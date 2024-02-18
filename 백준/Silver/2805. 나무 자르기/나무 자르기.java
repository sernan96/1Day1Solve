    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class Main {
        static int n, m, l, min=0, max= -1;
        public static void main(String []args) throws IOException{
            int [] arr= input();
            binary_search(arr);
        }

        private static void binary_search(int [] arr) {
            while(min < max) {
                int mid = (min + max) / 2;
                long sum = 0;
                for(int tree_length : arr) {
                    if(tree_length - mid > 0) {
                        sum += (tree_length - mid);
                    }
                }
                if(sum < m) {
                    max = mid;
                }
                else {
                    min = mid + 1;
                }
            }
            System.out.println(min-1);
        }

        private static int [] input() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder result  = new StringBuilder();
            StringTokenizer nm = new StringTokenizer(br.readLine());
            n = Integer.parseInt(nm.nextToken());
            m = Integer.parseInt(nm.nextToken());
            int []arr = new int[n];
            StringTokenizer tree_length = new StringTokenizer(br.readLine());
            for(int i =0; i<n; i++){
                l = Integer.parseInt(tree_length.nextToken());
                arr[i] = l;
                max = Math.max(max, l);
            }
            return arr;
        }
    }