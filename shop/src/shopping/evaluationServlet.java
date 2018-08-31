package shopping;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/evaluationServlet")
public class evaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public evaluationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//BuyItemservlet参考
		Enumeration<String> names = request.getParameterNames();

		String name  = "";		// 現在のパラメータ名
		String itemId= "";		// 商品ID
		int evaluationNumber = 0;	// 評価数値

		evaluationNumber  = Integer.parseInt(request.getParameter("evaluation"));

		// 購入ボタンがクリックされた場所を特定
		// 今回のサンプルプログラムの場合、クリックされた購入ボタンの値（value）と、リストボックスの値が取得できる
		// 購入ボタンをクリックした後のURLにパラメータが記載されています
		//
		while(names.hasMoreElements()) {

			// 渡ってきたパラメータを順番に処理
			// パラメータ名を取得
			name = names.nextElement();

			// 購入ボタンがクリックされている場合は「購入」のパラメータが取得できる
			if("評価".equals(request.getParameter(name))) {

				// 購入ボタンに付属している値（value）が商品IDになる
				itemId = name;
			}
		}

		// 商品情報を取得
		Shopping shopping = new Shopping();
		ItemBean bean = shopping.getItem(itemId);

		// 商品一覧をリクエストスコープの属性にセット
		request.setAttribute("item", bean);
		request.setAttribute("evaluationNumber", evaluationNumber);


		// 購入確認画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("/contents/evaluation.jsp");
		rd.forward(request, response);
	}
}