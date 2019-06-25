package wordladder;

import java.util.*;
import java.io.*;

public class WordLadder{
    String start;
    String destination;
    Set<String> words = new HashSet<String>();
    String ans;

    public WordLadder(){
    }

    public void readEnglish(){
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        // System.out.println("Please enter filename containing source text:");
        // while(true){
        //     try {
        //         str = br.readLine();
        //         System.out.println(str);
        //         File file=new File(str);
        //         if(file.isDirectory()) break;
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
        System.out.println("Please enter filename containing source text:");
        while(true){
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            File file =new File(str);
            if(file.exists()) break;
        }
        try {
			FileReader fr = new FileReader(str);
			BufferedReader bf = new BufferedReader(fr);
			String str1;
			while ((str1 = bf.readLine()) != null) {
				words.add(str1);
			}
			bf.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void inputWords(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("Enter start word (enter ':q' to quit):");
        try {
            s = br.readLine();
            if(s.equals(":q"))  System.exit(0);
            start = s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Enter destination word:");
        String d;
        try {
            d = br.readLine();
            destination = d;
        } catch (IOException e) {
            e.printStackTrace();
        }
        start = start.replaceAll("[^a-z^A-Z]", "");
        start = start.toLowerCase();
        destination = destination.replaceAll("[^a-z^A-Z]", "");
        destination = destination.toLowerCase();
    }

    public void showAns(){
        Set<String> used = new HashSet<String>();
        String tmp;
        String wtmp,tmp1;
        Queue<String> q = new LinkedList<String>();
        HashMap<String, String> hs = new HashMap<String, String>();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        Boolean flag = false,find = false;
        int l = 0;
        ans = "";

        //initialize the variable and the container
        while(!q.isEmpty())   q.poll();
        used.clear();
        find = false;
        flag = false;

        //begin a research and count the time
        // clock_t startTime,endTime;
        // startTime = clock();
        //initialize the input and judge if the input is legal
        if(!words.contains(start))   {System.out.println("no ladder exists\n");return ;}
        if(!words.contains(destination))   {System.out.println("no ladder exists\n");return ;}
        if(start.length() != destination.length())    System.out.println("no ladder exists\n");
        else
        {
        //create the first ladder
            tmp = start;
            used.add(start);
            q.offer(tmp);
            hm.put(start, 1);
            while(!q.isEmpty())//if “q.size() == 0” means that all probable word have used
            {
                tmp = q.poll();//read the first ladder
                // for(int i = 0; i < tmp.size(); ++i)
                // {
                //     System.out.println(tmp.get(i));
                //     System.out.println("->");
                // }
                // System.out.println("\n");
                l = hm.get(tmp);
                wtmp = tmp;//read the last word of the ladder
                //judge if it is a right ladder
                if(wtmp.equals(destination) && !find)
                {
                    System.out.print("Found ladder:");
                    System.out.print(tmp);
                    ans += tmp;
                    while(hs.containsKey(tmp))
                    {
                        System.out.print(" <- ");
                        ans += " <- ";
                        tmp = hs.get(tmp);
                        System.out.print(tmp);
                        ans += tmp;
                    }
                    System.out.print("\n");
                    find = true;
                    break;
                }
                //if(find) break;//make sure the ladder is the shortest
                //change the last word of the ladder and get a new word
                for(int i = 0 ; i < wtmp.length() ; ++i)
                {
                    for(char j = 'a' ; j <= 'z' ; ++j)
                    {
                        if(wtmp.charAt(i) != j)  
                        {
                            tmp1 = wtmp;
                            StringBuilder strBuilder = new StringBuilder(tmp1);
                            strBuilder.setCharAt(i, j);
                            tmp1 = strBuilder.toString();
                            flag = false;
                            if(!words.contains(tmp1))   flag = true;//judge if it is a legal English word
                            if(!flag)
                            {
                                if(used.contains(tmp1))    flag = true;//judge if it has existed in one of the ladders
                            }
                            if(!flag)
                            {
                                //create a new ladder and push into the last of the queue
                                if(tmp1.equals(destination))  used.add(tmp1);
                                q.offer(tmp1);
                                if(!hs.containsKey(tmp1))
                                {
                                    hs.put(tmp1, tmp);
                                    hm.put(tmp1, l + 1);
                                }
                            }
                        }
                    }
                }
            }
            if(!find)   System.out.println("no ladder exists\n\n");//judge if there is a right ladder
        }
        // end the research and output the time used
        // endTime = clock();
        // cout << "Totle Time : " <<(double)(endTime - startTime) / CLOCKS_PER_SEC << "s" << endl;
        
    }
}