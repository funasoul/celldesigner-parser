package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.wrapper.ObjectFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class Layout2CDConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 26, 2016
 */

public class Layout2CDConverter extends BaseLayoutConverter {

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
		
		SBMLDocument document2 = SBMLLevelandVersionHandler.downgrade(document.clone());
		mWrapper = ObjectFactory.unmarshalSBMLFromString(JSBML.writeSBMLToString(document2));
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
		mWrapper.setModelVersion(4);
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
			Species s = (Species) sg.getSpeciesInstance();
			mWrapper.createSpeciesAliasWrapper(sg);
		}
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
		}
	}
	
	/**
	 * Convert text to CD.
	 *
	 * @param tgList the tg list
	 */
	public void convertTextToCD(List<TextGlyph> tgList){
		for(TextGlyph tg : tgList){
			
		}
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.layoutconverter.abstractLayoutConverter#save()
	 */
	@Override
	public void save() {
		try {
			File file = ObjectFactory.saveModel(mWrapper, outputFileName);
			document = SBMLReader.read(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
