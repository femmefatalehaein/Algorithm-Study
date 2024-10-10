package 달이차오른다가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자_장희주 {

    static class Node{
        int x, y,dist,key;

    public Node(int x, int y, int dist, int key) {
        super();
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.key = key;
    }
    

    
    
}

static int N,M;
static int result;
static char [][] maze;
static int startX,startY;
static int[] dx= {-1,1,0,0};
static int[] dy= {0,0,-1,1};

public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer(br.readLine());
    //가로세로크기 
    N=Integer.parseInt(st.nextToken());
     M=Integer.parseInt(st.nextToken());
    maze=new char[N][M];
    result=-1;
    for(int i=0;i<N;i++) {
        String str=br.readLine();
        for(int j=0;j<M;j++) {
            maze[i][j]=str.charAt(j);
            //0일때는 출발위치
            if(maze[i][j]=='0') {
                startX=i;
                startY=j;
            
            }
        }
    }
    bfs(startX,startY);
    System.out.println(result);
}
//bfs탐색
static void bfs(int x, int y) {
	//방문여부
	//비트 마스킹으로 열쇠정보 저장
    boolean[][][] visited=new boolean[N][M][1<<6];
    Queue<Node> q=new ArrayDeque<>();
    q.offer(new Node(x,y,0,0));
    visited[x][y][0]=true;
    while(!q.isEmpty()) {
        Node current=q.poll();
        //x 좌표 
        int cx=current.x;
        //y 좌표
        int cy=current.y;
        //거리
        int dist=current.dist;
        //키정보
        int key=current.key;
        for(int i=0;i<4;i++) {
            int nx=cx+dx[i];
            int ny=cy+dy[i];
            //범위 벗어난다면
            if(nx<0 || ny<0 || nx>=N || ny>=M ||maze[nx][ny]=='#' || visited[nx][ny][key]) {
                continue;
            }
            //빈칸
            if(maze[nx][ny]=='.' || maze[nx][ny]=='0') {
                q.offer(new Node(nx,ny,dist+1,key));
                visited[nx][ny][key]=true;
                
            }
            //열쇠
            else if(maze[nx][ny]>='a'&& maze[nx][ny]<='f') {
                int tempkey=key | 1 << maze[nx][ny]-'a';
                q.offer(new Node(nx,ny,dist+1,tempkey));
                visited[nx][ny][tempkey]=true;
            }
            //문
            else if(maze[nx][ny]>='A' && maze[nx][ny]<='F') {
                if((key & 1 << maze[nx][ny]-'A') > 0) {
                    q.offer(new Node(nx,ny,dist+1,key));
                    visited[nx][ny][key]=true;
                }
            }
            //출구
            else if(maze[nx][ny]=='1') {
                result=dist+1;
                return;
            }
        }
    }
}
}