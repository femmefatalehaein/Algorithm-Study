import java.io.*;
import java.util.*;

public class Solution{
	public static void main(String[] args) throws IOException	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = null;
		
		for(int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			int arr[][] = new int[n][n];
			for(int i = 0; i < n; i++) {
				stn = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(stn.nextToken());
				}
			}
			
			for(int i = 0; i < n; i++) {
				if(arr[0][i] == 1) {
					double weight = 1;
					int k = 1;
					int count = 0;
					for(int j = 1; j < n; j = j + k) {
						int weight2 = 1;
						if(arr[j][i] == 0) {
							k = 1;
							if(j < n) {
								arr[j][i] = 1;
								arr[j-1-count][i] = 0;
								weight = weight * 1.9;
								continue;
							}
						}
						else if(arr[j][i] == 1) {
							k = 1;
							while(true) {
								if(j+k >= n) {
									break;
								}
								if(arr[j+k][i] == 1) {
									weight2 = weight2 + 1;
									k++;
								}
								else if(arr[j+k][i] == 0) {
									break;
								}
							}
							
							if(weight <= weight2) {
								break;	
							}
							else if(weight > weight2) {
								count = count + k;
								for(int x = 0; x < k; x++) {
									weight = weight * 1.9;
								}
							}
						}
					}
				}
			}
			
			for(int i = 0; i < n; i++) {
				if(arr[i][0] == 1) {
					double weight = 1;
					int k = 1;
					int count = 0;
					for(int j = 1; j < n; j = j + k) {
						int weight2 = 1;
						if(arr[i][j] == 0) {
							k = 1;
							if(j < n) {
								arr[i][j] = 1;
								arr[i][j-1-count] = 0;
								weight = weight * 1.9;
								continue;
							}
						}
						else if(arr[i][j] == 1) {
							k = 1;
							while(true) {
								if(j+k >= n) {
									break;
								}
								if(arr[i][j+k] == 1) {
									weight2 = weight2 + 1;
									k++;
								}
								else if(arr[i][j+k] == 0) {
									break;
								}
							}
							
							if(weight <= weight2) {
								break;	
							}
							else if(weight > weight2) {
								count = count + k;
								for(int x = 0; x < k; x++) {
									weight = weight * 1.9;
								}
							}
						}
					}
				}
			}
			
			int colcount = 0;
			int rowcount = 0;
			for(int i = 0; i < n; i++) {
				if(arr[i][n-1] == 1) {
					rowcount++;
				}
				if(arr[n-1][i] == 1) {
					colcount++;
				}
			}
			System.out.println("#" + t + " " + colcount + " " + rowcount);
		}
	}
}