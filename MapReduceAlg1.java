import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MapReduceAlg1 {

	/**
	 * @author alexis.saari
	 * 
	 *         This class extends MapReduceBase to accept file input of the format
	 *         <person> <friend1> <friend2> ... <friendN>
	 */
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
		public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			StringTokenizer tokenizer = new StringTokenizer(value.toString(), "\n");
			String line = null;
			
			while (tokenizer.hasMoreTokens()) {
				line = tokenizer.nextToken();
				
				//Sending line to Reducer without any sorting or parsing
				output.collect(line, password);
				
			}
		}
	}

	/**
	 * @author alexis.saari
	 *
	 *         This class extends MapReduceBase to take in the output from the Map
	 *         class. This class compares the <person> and their <friend> along with
	 *         the person's remaining <friends> to create a list of their common
	 *         friends.
	 */
	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterator<org.w3c.dom.Text> values, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			Text[] texts = new Text[2];
			int index = 0;
			while (values.hasNext()) {
				texts[index++] = new Text(values.next());
			}
			
			String guess = texts[0].toString();
			String password = texts[1].toString();
			List<String> list = new LinkedList<String>();
			
			if (guess.equals(password)) {
				output.collect(key, new Text("Password found is: " + password));
				return;
			} else {
				output.collect(key, new Text(guess));
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 * 
	 *             The main method instantiates JobConf object which we set our
	 *             classes, names, keys, and values to. We then set our input and
	 *             output files and run the job.
	 */
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(MapReduceAlg1.class);
		conf.setJobName("MainFriend");

		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);

		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(Text.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		JobClient.runJob(conf);
	}
}
