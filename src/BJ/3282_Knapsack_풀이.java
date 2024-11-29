import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class KnapsackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int n = Integer.parseInt(request.getParameter("n")); // 물건의 개수
        int W = Integer.parseInt(request.getParameter("W")); // 배낭의 최대 용량

        int[] weights = new int[n];
        int[] values = new int[n];

        // 물건의 무게와 가치를 받아서 배열에 저장
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(request.getParameter("weight" + i));
            values[i] = Integer.parseInt(request.getParameter("value" + i));
        }

        // 동적 계획법을 이용해 Knapsack 문제를 해결
        int[][] K = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    K[i][w] = Math.max(K[i - 1][w], K[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    K[i][w] = K[i - 1][w];
                }
            }
        }

        // 결과를 JSP로 전달
        request.setAttribute("maxValue", K[n][W]); // 최적의 값 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }
}
