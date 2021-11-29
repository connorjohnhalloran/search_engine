
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Server {

    public static class Pair {
        int k;
        int v;

        public Pair(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    public static ArrayList<File> files;
    public static HashSet<String> stoplist;
    public static HashMap<String, ArrayList<Pair>> ii;

    public static void main(String[] args) throws FileNotFoundException {

        files = new ArrayList<File>();
        stoplist = new HashSet<String>();
        ii = new HashMap<>();

        collect_files();
        generate_stoplist();

        // Print stoplist
        //for (String s : stoplist) { System.out.println(s); }
        //for (File f : files) { System.out.println(f.getName()); }

        int file_index = 1;
        for (File f : files) {
            Scanner s = new Scanner(f);
            int i = 0;
            int word_num = 0;
            while (s.hasNext()) {
                word_num += 1;

                String current = s.next().toLowerCase()
                        .replace("\"", "")
                        .replace("'s", "")
                        .replace(".", "")
                        .replace("?", "")
                        .replace(",", "")
                        .replace(";", "")
                        .replace(":", "")
                        .replace("(", "")
                        .replace(")", "")
                        ;

                //if (!current.equals("friend")) { continue; }

                if (stoplist.contains(current)) {
                    System.out.println("STOP! Total stopped: " + i);
                    i += 1;
                    continue;
                }

                // Words that are not on the stoplist reach here
                if (!ii.containsKey(current)) {
                    ii.put(current, new ArrayList<>());
                }
                ii.get(current).add(new Pair(file_index, word_num));
            }
            file_index += 1;
        }

        for (Map.Entry<String, ArrayList<Pair>> a : ii.entrySet()) {
            System.out.print(a.getKey() + ":\t");
            ArrayList<Pair> x = a.getValue();
            System.out.print(x.size());
            System.out.println();
            for (Pair p : x) { System.out.print("{" + files.get(p.k-1).getName() + ", " + p.v + "}, "); }
            System.out.println();


        }
    }

    public static void collect_files() throws FileNotFoundException {
        //File dir = new File("/Users/connorhalloran/Documents/text_files");
        File dir = new File("/Users/connorhalloran/Downloads/Data");
        files.addAll(Arrays.asList(dir.listFiles()));


    }

    public static void inv_ind() {
    }


    public static void top_n() {
    }

    public static void generate_stoplist() {
        String[] words = { "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are",
                "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but",
                "by", "can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing",
                "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hadn't", "has", "hasn't",
                "have", "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself",
                "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is",
                "isn't", "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no",
                "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours", "ourselves", "out",
                "over", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some",
                "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there",
                "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", "to",
                "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were",
                "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's",
                "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're",
                "you've", "your", "yours", "yourself", "yourselves"};
        stoplist.addAll(Arrays.asList(words));
    }

}
