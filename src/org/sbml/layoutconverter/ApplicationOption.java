package org.sbml.layoutconverter;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationOption.
 *
 * @author Kaito Ii
 * 
 * Date Created: Aug 11, 2016
 */

public class ApplicationOption {
	
	@Option(name = "-h", aliases= {"--help"}, required = false, usage = "help")
	private boolean help = false;
	
	/** The CD 2 layout. */
	@Option(name = "-c", aliases= {"--CD2Layout"}, required = false, usage = "convert CD to layout")
	private Boolean CD2Layout;
	
	/** The Layout 2 CD. */
	@Option(name = "-l", aliases= {"--Layout2CD"}, required = false, usage = "convert layout to CD")
	private Boolean Layout2CD;
	
	/** The default compartment. */
	@Option(name = "-d", aliases= {"--defaultCompartment"}, required = false, usage = "convert default compartment")
	private Boolean defaultCompartment;
	
	/** The input. */
	@Argument(index = 0, metaVar = "input files", required = true, usage = "input file")
    private String input;
	
	/** The output. */
	@Argument(index = 1, metaVar = "output files", required = false, usage = "output file")
    private String output;

	/**
	 * @return the help
	 */
	public boolean isHelp() {
		return help;
	}
	
	/**
	 * Checks if is CD 2 layout.
	 *
	 * @return the cD2Layout
	 */
	public boolean isCD2Layout() {
		return CD2Layout;
	}

	/**
	 * Checks if is sets the CD 2 layout.
	 *
	 * @return true, if is sets the CD 2 layout
	 */
	public boolean issetCD2Layout() {
		if(CD2Layout == null)
			return false;
		
		return true;
	}

	/**
	 * Checks if is layout 2 CD.
	 *
	 * @return the layout2CD
	 */
	public boolean isLayout2CD() {
		return Layout2CD;
	}

	/**
	 * Checks if is sets the layout 2 CD.
	 *
	 * @return true, if is sets the layout 2 CD
	 */
	public boolean issetLayout2CD() {
		if(Layout2CD == null)
			return false;
		
		return true;
	}

	/**
	 * Checks if is default compartment.
	 *
	 * @return the defaultCompartmen
	 */
	public boolean isDefaultCompartment() {
		if(defaultCompartment == null)
			return true;
			
		return defaultCompartment;
	}

	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * Gets the output.
	 *
	 * @return the output
	 */
	public String getOutput() {
		if(output == null)
			return "";
		
		return output;
	}

}
