package tuffy.parse;
import org.kohsuke.args4j.Option;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import tuffy.util.Config;
/**
 * Parser for command line options.
 */
public class CommandOptions {
	
	public String toString()
	{
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public enum MAPInferAlgo {WALK, SWEEP};
	public enum InferDataStore {RAM, DISK};
	
	
    @Option(name="-snap", required=false, usage="Whether takes snapshot while MCSAT inference.")
    public boolean snapshot = false;
	
	/**
	 * Essential input/output
	 */
    @Option(name="-i", aliases="-mln", required=true, usage="REQUIRED. Input MLN program(s). Separate with comma.")
    public String fprog;

    @Option(name="-e", aliases="-evidence", required=false, usage="REQUIRED. Input evidence file(s). Separate with comma.")
    public String fevid;

    @Option(name="-q", aliases="-query", usage="Query atom(s). Separate with comma.")
    public String queryAtoms;

    @Option(name="-queryFile", usage="Input query file(s). Separate with comma.")
    public String fquery;

    @Option(name="-o", aliases={"-r","-result"}, required=true, usage="REQUIRED. Output file.")
    public String fout;

    
    /**
     * Auxiliary input/output
     */
    @Option(name="-db", usage="The database schema from which the evidence is loaded.")
    public String evidDBSchema = null;
    
    @Option(name="-dbNeedTranslate", usage="Whether we can directly use tables in this DB schema. See -db option.")
    public boolean dbNeedTranslate = false;
    
    @Option(name="-gz", usage="Compress output files in gzip.")
    public boolean outputGz = false;
    
    @Option(name="-keepData", usage="Keep the data in the database upon exiting.")
    public boolean keepData;
    
    @Option(name="-dribble", usage="File where terminal output will be written to.")
    public String fDribble = null;

    @Option(name="-printResultsAsPrologFacts", usage="print Results As Prolog Facts.")
    public boolean outputProlog;
    
    @Option(name="-lineHeader", usage="Precede each line printed on the console with this string.")
    public String consoleLineHeader = null;
    
    @Option(name="-seed", usage="Seed for Java random number generator (Default = 0, which picks a random seed")
    public long seed = 0;
    
    @Option(name="-pgSeed", usage="Seed for Postgres random number generator between 0.0 and 1.0 (Default = 0, which picks a random seed")
    public float pgSeed = 0.0f;

    @Option(name="-writeClauses", usage="File to write the grounded MLN")
    public String writeClausesFile = null;
    
    @Option(name="-writeWCNF", usage="File to write the grounded WCNF")
    public String writeWCNFFile = null;
    
    @Option(name="-unitPropagate", usage="Run unit propagation on the grounded MLN (potentially buggy, disabled)")
    public boolean unitPropagate = false;
    
    @Option(name="-iterativeUnitPropagate", usage="Run iterative unit propagation during grounding")
    public boolean iterativeUnitPropagate = false;
    
    @Option(name="-useBackbones", usage="Compute backbones instead of units with Glucose")
    public boolean useBackbones = false;
    
    @Option(name="-glucosePath", usage="Use Glucose for iterative unit propagation")
    public String glucosePath = null;

    //@Option(name="-psec", usage="Dump MCSAT results every [psec] seconds.")
    public int mcsatDumpPeriodSec = 0;
    
    @Option(name="-verbose", usage="Verbose level (0-3). Default=0")
    public int verboseLevel = 0;
    
    @Option(name="-minProb", usage="Mininum probability for output of marginal inference.")
    public double minProb = 0;

    @Option(name="-softT", usage="Threshold for activating soft evidence.")
    public double softT = 0;

    
    /**
     * Misc
     */
    @Option(name="-conf", usage="Path of the configuration file. Default='./tuffy.conf'")
    public String pathConf = null;

    @Option(name="-help", usage="Display command options.")
    public boolean showHelp = false;

    @Option(name="-timeout", usage="Timeout in seconds.")
    public int timeout = 0;
    
    @Option(name="-groundingTimeout", usage="Grounding timeout in seconds.")
    public int groundingTimeout = 0;

    @Option(name="-rawString", usage="Store constants as raw strings instead of normalizing them into integer IDs.")
    public boolean constantAsRawString = false;
    
    
    
    /**
     * Mode selection
     */
    @Option(name="-marginal", usage="Run marginal inference with MC-SAT.")
    public boolean marginal = false;
    
    @Option(name="-mle", usage="Run MLE inference.")
    public boolean mle = false;

    @Option(name="-dual", usage="Run both MAP and marginal inference. Results will be " +
    		"written to $out_file.map and $out_file.marginal respectively.")
    public boolean dual = false;
    
    @Option(name="-learnwt", usage="Run Tuffy in discriminative weight learning mode.")
    public boolean isDLearningMode = false;

    @Option(name="-nopart", usage="Disable MRF partitioning. (Partitioning is enabled by default.)")
    public boolean disablePartition = false;

    @Option(name="-threads", usage="The max num of threads to run in parallel. Default = #available processors")
    public Integer maxThreads = 0;
    
    
    
    /**
     * Inference/learning parameters
     */
    @Option(name="-maxFlips", usage="Max number of flips per try. Default=[#atoms] X 100")
    public long maxFlips = 0;

    @Option(name="-maxTries", usage="Max number of tries in WalkSAT. Default=3")
    public int maxTries = 0;

    @Option(name="-cw", aliases="-closedWorld", usage="Specify closed-world predicates. Separate with comma.")
    public String cwaPreds;

    @Option(name="-activateAll", usage="Mark all unknown atoms as active during grounding.")
    public boolean activateAllAtoms;

    @Option(name="-dontBreak", usage="Forbid WalkSAT steps that would break hard clauses. DEFAULT=FALSE")
    public boolean avoidBreakingHardClauses;
    
    @Option(name="-mcsatSamples", usage="Number of samples used by MC-SAT. Default=100.")
    public int mcsatSamples = 100;

    @Option(name="-mcsatDumpInt", usage="Number of samples between dumps from MC-SAT. Default=Never.")
    public int mcsatDumpInt = 0;
    
    @Option(name="-mcsatCumulative", usage="Cumulate samples of MC-SAT. Default=No.")
    public boolean mcsatCumulative = false;
    
    @Option(name="-innerPara", usage="[Default=1] Parallism for MLE sampler.")
    public int innerPara = 1;

    @Option(name="-saProb", usage="Probability of performing a simulated annealing step during "
    		+ "SampleSAT. DEFAULT=0.5")
    public double simulatedAnnealingSampleSATProb = 0.5;
    
    @Option(name="-mcsatParam", usage="Set x; each step of MC-SAT retains each " +
    		"non-violated clause with probability 1-exp(-|weight|*x). DEFAULT=1.")
    public double mcsatPara = 1;
    
    @Option(name="-dMaxIter", usage="Max number of iterations for learning. DEFAULT=500")
    public int nDIteration = 500;

    @Option(name="-nopush", usage="Do not push down queries to MRF components.")
    public boolean noPushDown = false;
    
    @Option(name="-gp", usage="Do not push down queries to MRF components.")
    public boolean gp = false;

    //@Option(name="-algo", usage="MAP inference algorithm. Default=WALK")
    //public MAPInferAlgo algo = MAPInferAlgo.WALK;
    
    //@Option(name="-allRuleAsMLN", usage="Regard all rules as MLN rules, instead of decomposite the program")
    //public boolean allRuleAsMLN = false;

    //@Option(name="-store", usage="Storage of inference data. Default=RAM")
    //public InferDataStore store = InferDataStore.RAM;

    //@Option(name="-block", usage="Whether use blocking for key-constraints")
    //public boolean block = false;
    
    
    ////////////////////////Elementary/////////////////////////
    @Option(name="-iterSpan", usage="If bootstrapping, set the span between two iterations.")
    public long iterSpan = -1;
    
    @Option(name="-sgdStepSize", usage="[DEFAULT=0.01] Initial Step size of sgd.")
    public double sgd_stepSize = 0.01;
    
    @Option(name="-sgdDecay", usage="[DEFAULT=0.9] Decay factor of sgd after each epoch.")
    public double sgd_decay = 0.9;
    
    @Option(name="-sgdMetaSample", usage="[DEFAULT=10] # Samples for each component in sgd.")
    public int sgd_metaSample = 10;
    
    @Option(name="-sgdMU", usage="[DEFAULT=0.001] MU in sgd.")
    public double sgd_mu = 0.001;
    
    @Option(name="-debug", usage="[DEFAULT=False] Debug mode.")
    public boolean debug = false;
    
    @Option(name="-key", usage="[DEFAULT=False] Enforce key constraint while sampling.")
    public boolean mle_use_key_constraint = false;
    
    @Option(name="-gibbs", usage="[DEFAULT=False] Use Gibbs sampling for MLE.")
    public boolean mle_use_gibbs = false;
    
    @Option(name="-mcsat", usage="[DEFAULT=False] Use MCSAT sampling for MLE.")
    public boolean mle_use_mcsat = false;
    
    @Option(name="-jt", usage="[DEFAULT=False] Use Junction Tree if possible.")
    public boolean mle_use_junction_tree = false;
    
    @Option(name="-serialmix", usage="[DEFAULT=-1] Use MCSAT/Naive Serial Mix sampling for MLE.")
    public int mle_serialmix = -1;
    
    @Option(name="-gibbsThinning", usage="[DEFAULT=10000] Thinning Steps.")
    public int mle_gibbs_thinning = 10;
    
    @Option(name="-partComponent", usage="[DEFAULT=FALSE] Partition Component into different chunks while sampling.")
    public boolean mle_part_component = false;

    @Option(name="-small", usage="[DEFAULT=FALSE] Do optimization on samll components.")
    public boolean mle_optimize_small_component = false;
    
    @Option(name="-log", usage="[DEFAULT=FALSE] Write samples to log file.")
    public boolean sampleLog = false;
    
    @Option(name="-sa", usage="SampleSAT Simulated Annealing \"Temperature\" (inverse of temp)")
    public double samplesat_sa_coef = Config.samplesat_sa_coef;
    
    @Option(name="-randomStep", usage="Specify the WalkSAT random step probability (the probability "
    		+"WalkSAT performs a non-greedy flip)")
    public double random_step = 0.5;
    
}















