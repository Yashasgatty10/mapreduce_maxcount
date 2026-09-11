import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxWord {

    public static class WordMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private static final IntWritable ONE = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            String[] words = value.toString()
                    .toLowerCase()
                    .split("\\s+");

            for (String w : words) {
                w = w.replaceAll("[^a-zA-Z0-9]", "");

                if (!w.isEmpty()) {
                    word.set(w);
                    context.write(word, ONE);
                }
            }
        }
    }

    public static class WordReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {

        private String maxWord = "";
        private int maxCount = 0;

        public void reduce(Text key, Iterable<IntWritable> values,
                            Context context)
                throws IOException, InterruptedException {

            int count = 0;

            for (IntWritable value : values) {
                count += value.get();
            }

            if (count > maxCount) {
                maxCount = count;
                maxWord = key.toString();
            }
        }

        @Override
        protected void cleanup(Context context)
                throws IOException, InterruptedException {

            context.write(new Text(maxWord),
                          new IntWritable(maxCount));
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "Maximum Occurring Word");

        job.setJarByClass(MaxWord.class);

        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}