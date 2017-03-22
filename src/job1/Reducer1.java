package job1;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class Reducer1 extends MapReduceBase implements Reducer<Text, Text, Text,
Text>{

	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		
		StringBuffer buf = new StringBuffer();
		
		while  (values.hasNext()){
			buf.append(values.next().toString()+",");
		}
		
		output.collect(key, new Text(buf.toString()));
		
	}

}
