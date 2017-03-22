package job1;
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class Mapper1 extends MapReduceBase
implements Mapper<LongWritable, Text, Text,Text>{

	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		
		String[] values = value.toString().split("\t");
		//System.out.println("Hiiiii "+values[0]);
		String valueFinal = values[1];
		output.collect(new Text(values[0]), new Text(valueFinal));
	}

}
