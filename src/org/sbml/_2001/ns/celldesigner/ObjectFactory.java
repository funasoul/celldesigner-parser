//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.24 at 05:21:09 PM JST 
//


package org.sbml._2001.ns.celldesigner;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sbml._2001.ns.celldesigner package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hypothetical_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "hypothetical");
    private final static QName _AntisensernaReference_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "antisensernaReference");
    private final static QName _ComplexSpecies_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "complexSpecies");
    private final static QName _Class_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "class");
    private final static QName _Homodimer_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "homodimer");
    private final static QName _Name_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "name");
    private final static QName _Alias_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "alias");
    private final static QName _Activity_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "activity");
    private final static QName _ModelVersion_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "modelVersion");
    private final static QName _ProteinReference_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "proteinReference");
    private final static QName _RnaReference_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "rnaReference");
    private final static QName _PositionToCompartment_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "positionToCompartment");
    private final static QName _Reaction_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "reaction");
    private final static QName _GeneReference_QNAME = new QName("http://www.sbml.org/2001/ns/celldesigner", "geneReference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sbml._2001.ns.celldesigner
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListOfRegions }
     * 
     */
    public ListOfRegions createListOfRegions() {
        return new ListOfRegions();
    }

    /**
     * Create an instance of {@link LayerSpeciesAlias }
     * 
     */
    public LayerSpeciesAlias createLayerSpeciesAlias() {
        return new LayerSpeciesAlias();
    }

    /**
     * Create an instance of {@link ListOfModifications }
     * 
     */
    public ListOfModifications createListOfModifications() {
        return new ListOfModifications();
    }

    /**
     * Create an instance of {@link Species }
     * 
     */
    public Species createSpecies() {
        return new Species();
    }

    /**
     * Create an instance of {@link ListOfComplexSpeciesAliases }
     * 
     */
    public ListOfComplexSpeciesAliases createListOfComplexSpeciesAliases() {
        return new ListOfComplexSpeciesAliases();
    }

    /**
     * Create an instance of {@link org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias }
     * 
     */
    public org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias createComplexSpeciesAlias() {
        return new org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias();
    }

    /**
     * Create an instance of {@link ReactionAnnotationType }
     * 
     */
    public ReactionAnnotationType createReactionAnnotationType() {
        return new ReactionAnnotationType();
    }

    /**
     * Create an instance of {@link ModelAnnotationType }
     * 
     */
    public ModelAnnotationType createModelAnnotationType() {
        return new ModelAnnotationType();
    }

    /**
     * Create an instance of {@link SpeciesAnnotationType }
     * 
     */
    public SpeciesAnnotationType createSpeciesAnnotationType() {
        return new SpeciesAnnotationType();
    }

    /**
     * Create an instance of {@link CompartmentAnnotationType }
     * 
     */
    public CompartmentAnnotationType createCompartmentAnnotationType() {
        return new CompartmentAnnotationType();
    }

    /**
     * Create an instance of {@link SpeciesReferenceAnnotationType }
     * 
     */
    public SpeciesReferenceAnnotationType createSpeciesReferenceAnnotationType() {
        return new SpeciesReferenceAnnotationType();
    }

    /**
     * Create an instance of {@link ModelDisplay }
     * 
     */
    public ModelDisplay createModelDisplay() {
        return new ModelDisplay();
    }

    /**
     * Create an instance of {@link DoubleLine }
     * 
     */
    public DoubleLine createDoubleLine() {
        return new DoubleLine();
    }

    /**
     * Create an instance of {@link ReactantLink }
     * 
     */
    public ReactantLink createReactantLink() {
        return new ReactantLink();
    }

    /**
     * Create an instance of {@link ListOfAntisenseRNAs }
     * 
     */
    public ListOfAntisenseRNAs createListOfAntisenseRNAs() {
        return new ListOfAntisenseRNAs();
    }

    /**
     * Create an instance of {@link Paint }
     * 
     */
    public Paint createPaint() {
        return new Paint();
    }

    /**
     * Create an instance of {@link Halo }
     * 
     */
    public Halo createHalo() {
        return new Halo();
    }

    /**
     * Create an instance of {@link ListOfProteins }
     * 
     */
    public ListOfProteins createListOfProteins() {
        return new ListOfProteins();
    }

    /**
     * Create an instance of {@link ListOfInternalOperatorsInBlockDiagram }
     * 
     */
    public ListOfInternalOperatorsInBlockDiagram createListOfInternalOperatorsInBlockDiagram() {
        return new ListOfInternalOperatorsInBlockDiagram();
    }

    /**
     * Create an instance of {@link InternalOperatorValueInBlockDiagram }
     * 
     */
    public InternalOperatorValueInBlockDiagram createInternalOperatorValueInBlockDiagram() {
        return new InternalOperatorValueInBlockDiagram();
    }

    /**
     * Create an instance of {@link StructuralStateAngle }
     * 
     */
    public StructuralStateAngle createStructuralStateAngle() {
        return new StructuralStateAngle();
    }

    /**
     * Create an instance of {@link KeyInfo }
     * 
     */
    public KeyInfo createKeyInfo() {
        return new KeyInfo();
    }

    /**
     * Create an instance of {@link Protein }
     * 
     */
    public Protein createProtein() {
        return new Protein();
    }

    /**
     * Create an instance of {@link Block }
     * 
     */
    public Block createBlock() {
        return new Block();
    }

    /**
     * Create an instance of {@link State }
     * 
     */
    public State createState() {
        return new State();
    }

    /**
     * Create an instance of {@link ListOfFreeLines }
     * 
     */
    public ListOfFreeLines createListOfFreeLines() {
        return new ListOfFreeLines();
    }

    /**
     * Create an instance of {@link ConnectScheme }
     * 
     */
    public ConnectScheme createConnectScheme() {
        return new ConnectScheme();
    }

    /**
     * Create an instance of {@link BoxSize }
     * 
     */
    public BoxSize createBoxSize() {
        return new BoxSize();
    }

    /**
     * Create an instance of {@link Canvas }
     * 
     */
    public Canvas createCanvas() {
        return new Canvas();
    }

    /**
     * Create an instance of {@link ListOfBlockDiagrams }
     * 
     */
    public ListOfBlockDiagrams createListOfBlockDiagrams() {
        return new ListOfBlockDiagrams();
    }

    /**
     * Create an instance of {@link LayerLineBounds }
     * 
     */
    public LayerLineBounds createLayerLineBounds() {
        return new LayerLineBounds();
    }

    /**
     * Create an instance of {@link Gene }
     * 
     */
    public Gene createGene() {
        return new Gene();
    }

    /**
     * Create an instance of {@link SingleLine }
     * 
     */
    public SingleLine createSingleLine() {
        return new SingleLine();
    }

    /**
     * Create an instance of {@link EffectTargetInBlockDiagram }
     * 
     */
    public EffectTargetInBlockDiagram createEffectTargetInBlockDiagram() {
        return new EffectTargetInBlockDiagram();
    }

    /**
     * Create an instance of {@link InternalOperatorInBlockDiagram }
     * 
     */
    public InternalOperatorInBlockDiagram createInternalOperatorInBlockDiagram() {
        return new InternalOperatorInBlockDiagram();
    }

    /**
     * Create an instance of {@link BaseReactant }
     * 
     */
    public BaseReactant createBaseReactant() {
        return new BaseReactant();
    }

    /**
     * Create an instance of {@link ListOfIncludedSpecies }
     * 
     */
    public ListOfIncludedSpecies createListOfIncludedSpecies() {
        return new ListOfIncludedSpecies();
    }

    /**
     * Create an instance of {@link EndPointInBlockDiagram }
     * 
     */
    public EndPointInBlockDiagram createEndPointInBlockDiagram() {
        return new EndPointInBlockDiagram();
    }

    /**
     * Create an instance of {@link ListOfSquares }
     * 
     */
    public ListOfSquares createListOfSquares() {
        return new ListOfSquares();
    }

    /**
     * Create an instance of {@link BaseReactants }
     * 
     */
    public BaseReactants createBaseReactants() {
        return new BaseReactants();
    }

    /**
     * Create an instance of {@link Bounds }
     * 
     */
    public Bounds createBounds() {
        return new Bounds();
    }

    /**
     * Create an instance of {@link BriefView }
     * 
     */
    public BriefView createBriefView() {
        return new BriefView();
    }

    /**
     * Create an instance of {@link ListOfRNAs }
     * 
     */
    public ListOfRNAs createListOfRNAs() {
        return new ListOfRNAs();
    }

    /**
     * Create an instance of {@link LayerCompartmentAlias }
     * 
     */
    public LayerCompartmentAlias createLayerCompartmentAlias() {
        return new LayerCompartmentAlias();
    }

    /**
     * Create an instance of {@link StructuralStates }
     * 
     */
    public StructuralStates createStructuralStates() {
        return new StructuralStates();
    }

    /**
     * Create an instance of {@link DegradedShapeInBlockDiagram }
     * 
     */
    public DegradedShapeInBlockDiagram createDegradedShapeInBlockDiagram() {
        return new DegradedShapeInBlockDiagram();
    }

    /**
     * Create an instance of {@link CompartmentAlias }
     * 
     */
    public CompartmentAlias createCompartmentAlias() {
        return new CompartmentAlias();
    }

    /**
     * Create an instance of {@link BlockDiagram }
     * 
     */
    public BlockDiagram createBlockDiagram() {
        return new BlockDiagram();
    }

    /**
     * Create an instance of {@link ListOfCatalyzedReactions }
     * 
     */
    public ListOfCatalyzedReactions createListOfCatalyzedReactions() {
        return new ListOfCatalyzedReactions();
    }

    /**
     * Create an instance of {@link Catalyzed }
     * 
     */
    public Catalyzed createCatalyzed() {
        return new Catalyzed();
    }

    /**
     * Create an instance of {@link Layer }
     * 
     */
    public Layer createLayer() {
        return new Layer();
    }

    /**
     * Create an instance of {@link ListOfSpeciesAliases }
     * 
     */
    public ListOfSpeciesAliases createListOfSpeciesAliases() {
        return new ListOfSpeciesAliases();
    }

    /**
     * Create an instance of {@link SpeciesTag }
     * 
     */
    public SpeciesTag createSpeciesTag() {
        return new SpeciesTag();
    }

    /**
     * Create an instance of {@link ListOfStructuralStates }
     * 
     */
    public ListOfStructuralStates createListOfStructuralStates() {
        return new ListOfStructuralStates();
    }

    /**
     * Create an instance of {@link SpeciesIdentity }
     * 
     */
    public SpeciesIdentity createSpeciesIdentity() {
        return new SpeciesIdentity();
    }

    /**
     * Create an instance of {@link BaseProduct }
     * 
     */
    public BaseProduct createBaseProduct() {
        return new BaseProduct();
    }

    /**
     * Create an instance of {@link ListOfExternalNamesForResidue }
     * 
     */
    public ListOfExternalNamesForResidue createListOfExternalNamesForResidue() {
        return new ListOfExternalNamesForResidue();
    }

    /**
     * Create an instance of {@link ModificationResidue }
     * 
     */
    public ModificationResidue createModificationResidue() {
        return new ModificationResidue();
    }

    /**
     * Create an instance of {@link EffectSiteInBlockDiagram }
     * 
     */
    public EffectSiteInBlockDiagram createEffectSiteInBlockDiagram() {
        return new EffectSiteInBlockDiagram();
    }

    /**
     * Create an instance of {@link ListOfInternalLinksInBlockDiagram }
     * 
     */
    public ListOfInternalLinksInBlockDiagram createListOfInternalLinksInBlockDiagram() {
        return new ListOfInternalLinksInBlockDiagram();
    }

    /**
     * Create an instance of {@link AntisenseRNA }
     * 
     */
    public AntisenseRNA createAntisenseRNA() {
        return new AntisenseRNA();
    }

    /**
     * Create an instance of {@link ExternalConnectionInBlockDiagram }
     * 
     */
    public ExternalConnectionInBlockDiagram createExternalConnectionInBlockDiagram() {
        return new ExternalConnectionInBlockDiagram();
    }

    /**
     * Create an instance of {@link ListOfGenes }
     * 
     */
    public ListOfGenes createListOfGenes() {
        return new ListOfGenes();
    }

    /**
     * Create an instance of {@link LinkAnchor }
     * 
     */
    public LinkAnchor createLinkAnchor() {
        return new LinkAnchor();
    }

    /**
     * Create an instance of {@link UsualView }
     * 
     */
    public UsualView createUsualView() {
        return new UsualView();
    }

    /**
     * Create an instance of {@link ListOfLayers }
     * 
     */
    public ListOfLayers createListOfLayers() {
        return new ListOfLayers();
    }

    /**
     * Create an instance of {@link Notes }
     * 
     */
    public Notes createNotes() {
        return new Notes();
    }

    /**
     * Create an instance of {@link RNA }
     * 
     */
    public RNA createRNA() {
        return new RNA();
    }

    /**
     * Create an instance of {@link DegradedInBlockDiagram }
     * 
     */
    public DegradedInBlockDiagram createDegradedInBlockDiagram() {
        return new DegradedInBlockDiagram();
    }

    /**
     * Create an instance of {@link ExternalNameForResidue }
     * 
     */
    public ExternalNameForResidue createExternalNameForResidue() {
        return new ExternalNameForResidue();
    }

    /**
     * Create an instance of {@link View }
     * 
     */
    public View createView() {
        return new View();
    }

    /**
     * Create an instance of {@link ListOfLineDirection }
     * 
     */
    public ListOfLineDirection createListOfLineDirection() {
        return new ListOfLineDirection();
    }

    /**
     * Create an instance of {@link TagBounds }
     * 
     */
    public TagBounds createTagBounds() {
        return new TagBounds();
    }

    /**
     * Create an instance of {@link TagEdgeLine }
     * 
     */
    public TagEdgeLine createTagEdgeLine() {
        return new TagEdgeLine();
    }

    /**
     * Create an instance of {@link ListOfBindingSitesInBlockDiagram }
     * 
     */
    public ListOfBindingSitesInBlockDiagram createListOfBindingSitesInBlockDiagram() {
        return new ListOfBindingSitesInBlockDiagram();
    }

    /**
     * Create an instance of {@link ProductLink }
     * 
     */
    public ProductLink createProductLink() {
        return new ProductLink();
    }

    /**
     * Create an instance of {@link ListOfTexts }
     * 
     */
    public ListOfTexts createListOfTexts() {
        return new ListOfTexts();
    }

    /**
     * Create an instance of {@link InternalLinkInBlockDiagram }
     * 
     */
    public InternalLinkInBlockDiagram createInternalLinkInBlockDiagram() {
        return new InternalLinkInBlockDiagram();
    }

    /**
     * Create an instance of {@link ListOfModification }
     * 
     */
    public ListOfModification createListOfModification() {
        return new ListOfModification();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link LineDirection }
     * 
     */
    public LineDirection createLineDirection() {
        return new LineDirection();
    }

    /**
     * Create an instance of {@link ListOfReactantLinks }
     * 
     */
    public ListOfReactantLinks createListOfReactantLinks() {
        return new ListOfReactantLinks();
    }

    /**
     * Create an instance of {@link Info }
     * 
     */
    public Info createInfo() {
        return new Info();
    }

    /**
     * Create an instance of {@link Offset }
     * 
     */
    public Offset createOffset() {
        return new Offset();
    }

    /**
     * Create an instance of {@link InnerPosition }
     * 
     */
    public InnerPosition createInnerPosition() {
        return new InnerPosition();
    }

    /**
     * Create an instance of {@link org.sbml._2001.ns.celldesigner.Modification }
     * 
     */
    public org.sbml._2001.ns.celldesigner.Modification createModification() {
        return new org.sbml._2001.ns.celldesigner.Modification();
    }

    /**
     * Create an instance of {@link ListOfEffectSitesInBlockDiagram }
     * 
     */
    public ListOfEffectSitesInBlockDiagram createListOfEffectSitesInBlockDiagram() {
        return new ListOfEffectSitesInBlockDiagram();
    }

    /**
     * Create an instance of {@link LayerFreeLine }
     * 
     */
    public LayerFreeLine createLayerFreeLine() {
        return new LayerFreeLine();
    }

    /**
     * Create an instance of {@link ListOfSpeciesTag }
     * 
     */
    public ListOfSpeciesTag createListOfSpeciesTag() {
        return new ListOfSpeciesTag();
    }

    /**
     * Create an instance of {@link EditPoints }
     * 
     */
    public EditPoints createEditPoints() {
        return new EditPoints();
    }

    /**
     * Create an instance of {@link org.sbml._2001.ns.celldesigner.Region }
     * 
     */
    public org.sbml._2001.ns.celldesigner.Region createRegion() {
        return new org.sbml._2001.ns.celldesigner.Region();
    }

    /**
     * Create an instance of {@link ListOfProductLinks }
     * 
     */
    public ListOfProductLinks createListOfProductLinks() {
        return new ListOfProductLinks();
    }

    /**
     * Create an instance of {@link ResidueInBlockDiagram }
     * 
     */
    public ResidueInBlockDiagram createResidueInBlockDiagram() {
        return new ResidueInBlockDiagram();
    }

    /**
     * Create an instance of {@link BindingRegion }
     * 
     */
    public BindingRegion createBindingRegion() {
        return new BindingRegion();
    }

    /**
     * Create an instance of {@link Line }
     * 
     */
    public Line createLine() {
        return new Line();
    }

    /**
     * Create an instance of {@link ListOfCompartmentAliases }
     * 
     */
    public ListOfCompartmentAliases createListOfCompartmentAliases() {
        return new ListOfCompartmentAliases();
    }

    /**
     * Create an instance of {@link LinkTarget }
     * 
     */
    public LinkTarget createLinkTarget() {
        return new LinkTarget();
    }

    /**
     * Create an instance of {@link Point }
     * 
     */
    public Point createPoint() {
        return new Point();
    }

    /**
     * Create an instance of {@link ListOfGroups }
     * 
     */
    public ListOfGroups createListOfGroups() {
        return new ListOfGroups();
    }

    /**
     * Create an instance of {@link ListOfResiduesInBlockDiagram }
     * 
     */
    public ListOfResiduesInBlockDiagram createListOfResiduesInBlockDiagram() {
        return new ListOfResiduesInBlockDiagram();
    }

    /**
     * Create an instance of {@link BindingSiteInBlockDiagram }
     * 
     */
    public BindingSiteInBlockDiagram createBindingSiteInBlockDiagram() {
        return new BindingSiteInBlockDiagram();
    }

    /**
     * Create an instance of {@link ListOfBindingRegions }
     * 
     */
    public ListOfBindingRegions createListOfBindingRegions() {
        return new ListOfBindingRegions();
    }

    /**
     * Create an instance of {@link ListOfModificationResidues }
     * 
     */
    public ListOfModificationResidues createListOfModificationResidues() {
        return new ListOfModificationResidues();
    }

    /**
     * Create an instance of {@link EffectInBlockDiagram }
     * 
     */
    public EffectInBlockDiagram createEffectInBlockDiagram() {
        return new EffectInBlockDiagram();
    }

    /**
     * Create an instance of {@link SpeciesAlias }
     * 
     */
    public SpeciesAlias createSpeciesAlias() {
        return new SpeciesAlias();
    }

    /**
     * Create an instance of {@link StartingPointInBlockDiagram }
     * 
     */
    public StartingPointInBlockDiagram createStartingPointInBlockDiagram() {
        return new StartingPointInBlockDiagram();
    }

    /**
     * Create an instance of {@link BaseProducts }
     * 
     */
    public BaseProducts createBaseProducts() {
        return new BaseProducts();
    }

    /**
     * Create an instance of {@link ListOfExternalConnectionsInBlockDiagram }
     * 
     */
    public ListOfExternalConnectionsInBlockDiagram createListOfExternalConnectionsInBlockDiagram() {
        return new ListOfExternalConnectionsInBlockDiagram();
    }

    /**
     * Create an instance of {@link LineType2 }
     * 
     */
    public LineType2 createLineType2() {
        return new LineType2();
    }

    /**
     * Create an instance of {@link ListOfRegions.Region }
     * 
     */
    public ListOfRegions.Region createListOfRegionsRegion() {
        return new ListOfRegions.Region();
    }

    /**
     * Create an instance of {@link LayerSpeciesAlias.Font }
     * 
     */
    public LayerSpeciesAlias.Font createLayerSpeciesAliasFont() {
        return new LayerSpeciesAlias.Font();
    }

    /**
     * Create an instance of {@link ListOfModifications.Modification }
     * 
     */
    public ListOfModifications.Modification createListOfModificationsModification() {
        return new ListOfModifications.Modification();
    }

    /**
     * Create an instance of {@link Species.Annotation }
     * 
     */
    public Species.Annotation createSpeciesAnnotation() {
        return new Species.Annotation();
    }

    /**
     * Create an instance of {@link ListOfComplexSpeciesAliases.ComplexSpeciesAlias }
     * 
     */
    public ListOfComplexSpeciesAliases.ComplexSpeciesAlias createListOfComplexSpeciesAliasesComplexSpeciesAlias() {
        return new ListOfComplexSpeciesAliases.ComplexSpeciesAlias();
    }

    /**
     * Create an instance of {@link org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias.BackupSize }
     * 
     */
    public org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias.BackupSize createComplexSpeciesAliasBackupSize() {
        return new org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias.BackupSize();
    }

    /**
     * Create an instance of {@link ReactionAnnotationType.Extension }
     * 
     */
    public ReactionAnnotationType.Extension createReactionAnnotationTypeExtension() {
        return new ReactionAnnotationType.Extension();
    }

    /**
     * Create an instance of {@link ModelAnnotationType.Extension }
     * 
     */
    public ModelAnnotationType.Extension createModelAnnotationTypeExtension() {
        return new ModelAnnotationType.Extension();
    }

    /**
     * Create an instance of {@link SpeciesAnnotationType.Extension }
     * 
     */
    public SpeciesAnnotationType.Extension createSpeciesAnnotationTypeExtension() {
        return new SpeciesAnnotationType.Extension();
    }

    /**
     * Create an instance of {@link CompartmentAnnotationType.Extension }
     * 
     */
    public CompartmentAnnotationType.Extension createCompartmentAnnotationTypeExtension() {
        return new CompartmentAnnotationType.Extension();
    }

    /**
     * Create an instance of {@link SpeciesReferenceAnnotationType.Extension }
     * 
     */
    public SpeciesReferenceAnnotationType.Extension createSpeciesReferenceAnnotationTypeExtension() {
        return new SpeciesReferenceAnnotationType.Extension();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "hypothetical")
    public JAXBElement<Boolean> createHypothetical(Boolean value) {
        return new JAXBElement<Boolean>(_Hypothetical_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "antisensernaReference")
    public JAXBElement<String> createAntisensernaReference(String value) {
        return new JAXBElement<String>(_AntisensernaReference_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "complexSpecies")
    public JAXBElement<String> createComplexSpecies(String value) {
        return new JAXBElement<String>(_ComplexSpecies_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "class")
    public JAXBElement<String> createClass(String value) {
        return new JAXBElement<String>(_Class_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "homodimer")
    public JAXBElement<BigInteger> createHomodimer(BigInteger value) {
        return new JAXBElement<BigInteger>(_Homodimer_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "alias")
    public JAXBElement<String> createAlias(String value) {
        return new JAXBElement<String>(_Alias_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "activity")
    public JAXBElement<String> createActivity(String value) {
        return new JAXBElement<String>(_Activity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "modelVersion")
    public JAXBElement<BigDecimal> createModelVersion(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ModelVersion_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "proteinReference")
    public JAXBElement<String> createProteinReference(String value) {
        return new JAXBElement<String>(_ProteinReference_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "rnaReference")
    public JAXBElement<String> createRnaReference(String value) {
        return new JAXBElement<String>(_RnaReference_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "positionToCompartment")
    public JAXBElement<String> createPositionToCompartment(String value) {
        return new JAXBElement<String>(_PositionToCompartment_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "reaction")
    public JAXBElement<String> createReaction(String value) {
        return new JAXBElement<String>(_Reaction_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sbml.org/2001/ns/celldesigner", name = "geneReference")
    public JAXBElement<String> createGeneReference(String value) {
        return new JAXBElement<String>(_GeneReference_QNAME, String.class, null, value);
    }

}
