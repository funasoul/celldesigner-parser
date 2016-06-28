package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
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
			
		}
	}

	/**
	 * Convert species to CD.
	 *
	 * @param sgList
	 *            the sg list
	 */
	public void convertSpeciesToCD(List<SpeciesGlyph> sgList) {

	}

	/**
	 * Convert reactions to CD.
	 *
	 * @param rgList
	 *            the rg list
	 */
	public void convertReactionsToCD(List<ReactionGlyph> rgList) {

	}
	
	/* (non-Javadoc)
	 * @see org.sbml.layoutconverter.abstractLayoutConverter#save()
	 */
	@Override
	public void save() {
		try {
			File file = ObjectFactory.saveModel(mWrapper);
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
