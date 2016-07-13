package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.wrapper.CompartmentAliasWrapper;
import org.sbml.wrapper.ModifierSpeciesReferenceWrapper;
import org.sbml.wrapper.ObjectFactory;
import org.sbml.wrapper.ReactionWrapper;
import org.sbml.wrapper.SpeciesAliasWrapper;
import org.sbml.wrapper.SpeciesReferenceWrapper;
import org.sbml.wrapper.SpeciesWrapper;


/**
 * The Class Layout2CDConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 26, 2016
 */

public class Layout2CDConverter extends BaseLayoutConverter {

	SBMLDocument downgrade_document;
	
	/**
	 * Instantiates a new layout 2 CD converter.
	 *
	 * @param file the file
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JAXBException the JAXB exception
	 */
	public Layout2CDConverter(File file) throws XMLStreamException, IOException, JAXBException {
		super(file);
		init();
	}
	
	/**
	 * Instantiates a new layout 2 CD converter.
	 *
	 * @param file the file
	 * @param outputFileName the output file name
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JAXBException the JAXB exception
	 */
	public Layout2CDConverter(File file, String outputFileName) throws XMLStreamException, IOException, JAXBException {
		super(file, outputFileName);
		init();
	}
	
	/**
	 * Inits the.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SBMLException the SBML exception
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 */
	public void init() throws IOException, SBMLException, JAXBException, XMLStreamException{
		if (!SBMLUtil.isSetLayoutNameSpace(document))
			throw new IOException("Missing Layout Namespace");
		
		downgrade_document = SBMLLevelandVersionHandler.downgrade(document.clone());
		mWrapper = ObjectFactory.unmarshalSBMLFromString(JSBML.writeSBMLToString(downgrade_document));
		model = document.getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.layoutconverter.abstractLayoutConverter#convert()
	 */
	@Override
	public void convert() {
		LayoutModelPlugin mplugin = (LayoutModelPlugin) (model.getPlugin("layout"));
		layout = mplugin.getLayout(0);

		convertModelToCD(layout);
		convertCompartmentsToCD(layout.getListOfCompartmentGlyphs());
		convertSpeciesToCD(layout.getListOfSpeciesGlyphs());
		convertReactionsToCD(layout.getListOfReactionGlyphs());
		convertTextToCD(layout.getListOfTextGlyphs());
	}

	/**
	 * Convert model to CD.
	 *
	 * @param layout
	 *            the layout
	 */
	public void convertModelToCD(Layout layout) {
		mWrapper.setDimension(layout.getDimensions().getWidth(),layout.getDimensions().getHeight());
		mWrapper.setModelVersion(SBMLUtil.DEFALUT_CELLDESIGNER_MODEL_VERSION);
	}

	/**
	 * Convert compartments to CD.
	 *
	 * @param cgList
	 *            the cg list
	 */
	public void convertCompartmentsToCD(List<CompartmentGlyph> cgList) {
		for(CompartmentGlyph cg: cgList){
			Compartment c = (Compartment) cg.getCompartmentInstance();
			if(!c.getId().equals("default"))
				mWrapper.createCompartmentAliasWrapper(cg);
			
		}
	}

	/**
	 * Convert species to CD.
	 *
	 * @param sgList
	 *            the sg list
	 */
	public void convertSpeciesToCD(List<SpeciesGlyph> sgList) {
		for(SpeciesGlyph sg: sgList){
			if(sg.isSetSpecies()){
				Species s = (Species) sg.getSpeciesInstance();
				SpeciesAliasWrapper saw = mWrapper.createSpeciesAliasWrapper(sg);

				int sboterm = SBMLUtil.intSBOTermForPROTEIN;
				if (sg.isSetSBOTerm()){
					sboterm = s.getSBOTerm();
				} else if(s.isSetSBOTerm()){
					sboterm = s.getSBOTerm();
				}
				mWrapper.createSpeciesObjectFromSBOTerm(sg, sboterm);
				String clazz = SBMLUtil.SBOTermToCDClass(sboterm); 
				SpeciesWrapper sw = mWrapper.getSpeciesWrapperById(s.getId());
				sw.setClazz(clazz);
				
				if(clazz.equals("PROTEIN")){
					sw.getSpeciesIdentity().setProteinReference(mWrapper.getProteinBySpeciesId(s.getId()).getId());
				} else if(clazz.equals("GENE")){
					sw.getSpeciesIdentity().setGeneReference(mWrapper.getGeneBySpeciesId(s.getId()).getId());
				} else if(clazz.equals("RNA")){
					sw.getSpeciesIdentity().setRnaReference(mWrapper.getRNABySpeciesId(s.getId()).getId());
				} else if(clazz.equals("ANTISENSE_RNA")){
					sw.getSpeciesIdentity().setAntisensernaReference(mWrapper.getAntisenseRNABySpeciesId(s.getId()).getId());
				} else {
					sw.getSpeciesIdentity().setName(sw.getId());
				}
				
				CompartmentGlyph cg = getCompartmentGlyphByCompartmentId(s.getCompartment());
				if(cg != null)
					sw.setPositionToCompartment(LayoutUtil.getPositionToCompartment(sg, cg));
				else 
					sw.setPositionToCompartment("inside");
			} else {
			// included species?	
			// TODO complex spcecies
			}
		}
	}

	/**
	 * Gets the compartment glyph by compartment id.
	 *
	 * @param id the id
	 * @return the compartment glyph by compartment id
	 */
	public CompartmentGlyph getCompartmentGlyphByCompartmentId(String id){
		for(CompartmentGlyph cg : layout.getListOfCompartmentGlyphs()){
			if(cg.getCompartmentInstance().getId().equals(id))
				return cg;
		}

		return null;
	}
	
	/**
	 * Convert reactions to CD.
	 *
	 * @param rgList
	 *            the rg list
	 */
	public void convertReactionsToCD(List<ReactionGlyph> rgList) {
		for(ReactionGlyph rg : rgList){
			Reaction r = (Reaction) rg.getReactionInstance();
			
			ListOf<SpeciesReference> reactantList = r.getListOfReactants();
			ListOf<SpeciesReference> productList = r.getListOfProducts();
			ListOf<ModifierSpeciesReference> modifierList = r.getListOfModifiers();
			ReactionWrapper rw = mWrapper.getReactionWrapperById(r.getId());
			
			for(SpeciesReference sr : reactantList){
				SpeciesReferenceWrapper srw = rw.getReactantWrapperById(sr.getSpecies());
				SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperBySpeciesId(sr.getSpecies());
				srw.setAlias(saw.getId());
			}
			
			for(SpeciesReference sr : productList){
				SpeciesReferenceWrapper srw = rw.getProductWrapperById(sr.getSpecies());
				SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperBySpeciesId(sr.getSpecies());
				
				srw.setAlias(saw.getId());
			}
			
			for(ModifierSpeciesReference msr : modifierList){
				ModifierSpeciesReferenceWrapper msrw = rw.getModifierWrapperById(msr.getSpecies());
				SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperBySpeciesId(msr.getSpecies());
				msrw.setAlias(saw.getId());
			}
		}
	}
	
	/**
	 * Convert text to CD.
	 *
	 * @param tgList the tg list
	 */
	public void convertTextToCD(List<TextGlyph> tgList){
		for(TextGlyph tg : tgList){
			SBase reference = tg.getReferenceInstance();
			if(reference instanceof CompartmentGlyph){
				Compartment c = (Compartment) ((CompartmentGlyph)reference).getCompartmentInstance();
				CompartmentAliasWrapper caw = mWrapper.getCompartmentAliasWrapperByCompartmentId(c.getId());
				Point namePoint = tg.getBoundingBox().getPosition();
				caw.setNameX(namePoint.getX());
				caw.setNameY(namePoint.getY());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.layoutconverter.abstractLayoutConverter#save()
	 */
	@Override
	public void save() {
		try {
			File file = ObjectFactory.saveModel(mWrapper, outputFileName);
			document = SBMLUtil.setMaths(SBMLReader.read(file), downgrade_document);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
