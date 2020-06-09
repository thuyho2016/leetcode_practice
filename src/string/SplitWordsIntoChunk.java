package string;

import java.util.ArrayList;
import java.util.List;

/**
uber-interview-questions

Given a character limit and a message, split the message up into annotated chunks without cutting words as, for example when sending the SMS "Hi Sivasrinivas, your Uber is arriving now!" with char limit 25, you should get
["Hi Sivasrinivas,(1/3)", "your Uber is arriving(2/3)", "now!(3/3)"]
 * 
  is simple with following steps
1. lets begin with position i=0
2. jump to i+25
3. from position i+25 go back unless you get a whitespace... lets say you got whitespace at i+23.
4. send sms from i to i+23 and then set i = i+24 and repeat from step 2
5. follow these steps unless you are out of boudary in step 2.
 * @author thanhho
 *
 */
public class SplitWordsIntoChunk {
	public static List<String> splitText(String s,int limit)
	{
		List<String> sentences=new ArrayList<String>();
		List<String> result=new ArrayList<String>();
		if(s==null || s.isEmpty()) return sentences;
		limit=limit-"(x/x)".length();
		String[] words=s.split("\\s+");
		for(int i=0;i<words.length;i++)
		{
			StringBuilder sb=new StringBuilder();
			sb.append(words[i]);
			while(i+1<words.length && sb.length()+words[i+1].length()+1<=limit)
			{
				sb.append(" "+words[i+1]);
				i++;
			}
			sentences.add(sb.toString());
		}
		int size=sentences.size();
		for(int j=0;j<sentences.size();j++)
		{
			result.add(sentences.get(j)+"("+(j+1)+"/"+size+")");
		}
		return result;
	}
}
