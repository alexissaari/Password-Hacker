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

public class MapReduceAlg2 {

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
			String[] passwords = { "rpCc", "5p8u", "nIzw", "Mq4A", "tZAx", "9Es0", "8eCo", "tzbs", "DVwq", "gUNo",
					"kxpq", "nYnH", "vwFb", "xAns", "yNfB", "HK3L", "oX5n", "uoBD", "H1xf", "NwJd", "DJLW", "qIOD",
					"4WT8", "nHXk", "AeQ4", "NDKL", "XhaX", "yFQ7", "Vdow", "r9py", "UOOI", "6Zue", "CE5c", "8WPr",
					"T1WY", "t1tO", "eCYH", "7KOB", "Zt7Q", "zftq", "tSK1", "jttJ", "Xng0", "2JW1", "re2U", "GkaR",
					"BpD9", "kjKM", "4b4T", "Nxtf" };
			int counter = 0;
			String guesses = "";
			String correctPasswordz = "";

			while (tokenizer.hasMoreTokens()) {
				line = tokenizer.nextToken();

				// output.collect(new Text(line), new Text(password));

				// Sending line to Reducer
				// guesses += line + " ";
				// counter++;
				// if (counter == 1000) {
				// output.collect(new Text(guesses), new Text(password));
				// guesses = "";
				// counter = 0;
				// }

				for (int i = 0; i < passwords.length; i++) {
					if (line.equals(passwords[i])) {
						output.collect(new Text(line), new Text(passwords[i]));
					}
				}

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
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			Text[] texts = new Text[2];
			int index = 0;
			while (values.hasNext()) {
				texts[index] = new Text(values.next());
				index++;
			}

			String[] guesses = texts[0].toString().split(" ");
			String[] passwords = { "rpCc", "5p8u", "nIzw", "Mq4A", "tZAx", "9Es0", "8eCo", "tzbs", "DVwq", "gUNo",
					"kxpq", "nYnH", "vwFb", "xAns", "yNfB", "HK3L", "oX5n", "uoBD", "H1xf", "NwJd", "DJLW", "qIOD",
					"4WT8", "nHXk", "AeQ4", "NDKL", "XhaX", "yFQ7", "Vdow", "r9py", "UOOI", "6Zue", "CE5c", "8WPr",
					"T1WY", "t1tO", "eCYH", "7KOB", "Zt7Q", "zftq", "tSK1", "jttJ", "Xng0", "2JW1", "re2U", "GkaR",
					"BpD9", "kjKM", "4b4T", "Nxtf" };
			List<String> list = new LinkedList<String>();

			// comparing guesses list to password
			for (String guess : guesses) {
				for (String password : passwords) {
					if (guess.equals(password)) {
						list.add(guess);
					}
				}
			}

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i));
				if (i != list.size() - 1)
					sb.append(" ");
			}
			output.collect(key, new Text(sb.toString()));

		}
	}

	public static class MyTimer {
		Long startTime;
		Long endTime;
		boolean isFound = false;

		public void startTimer() {
			startTime = System.currentTimeMillis();
		}

		public void endTimer() {
			isFound = true;
			endTime = System.currentTimeMillis();
		}

		public Long totalTime() {
			return (long) ((endTime - startTime) / 1000.00);
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
		JobConf conf = new JobConf(MapReduceAlg2.class);
		conf.setJobName("MainFriend");

		MyTimer myTimer = new MyTimer();
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);

		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(Text.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		myTimer.startTimer();
		JobClient.runJob(conf);
		myTimer.endTimer();
		System.out.println("Time took: " + myTimer.totalTime().toString());
	}
}
