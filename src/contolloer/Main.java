package contolloer;

import java.io.IOException;
import java.util.ArrayList;

import model.Player;
import model.Player.PlayerStatus;
import model.Role.RoleName;
import model.StatusManage;
import model.StatusManage.Phase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		//　プレイヤー情報を取得します
		String playerName = req.getParameter("player_name");
		Player player = StatusManage.getPlayer(playerName);
		req.setAttribute("main_player", player);
		
		// プレイヤーリストを取得します
		ArrayList<Player> playerList = StatusManage.getPlayerList();
		req.setAttribute("player_list", playerList);
		
		//　投票がある場合は、投票処理を行う
		if ( req.getParameter("voted_player") != null) {
			
			Player votedPlayer = StatusManage.getPlayer(req.getParameter("voted_player"));
			StatusManage.getVoteLogic().vote(votedPlayer);
			StatusManage.initVoteLogic();
			
		}
		
		//　夜のアクションがある場合は、アクション処理を行う
		if ( req.getParameter("target_player") != null) {
			
			Player targetPlayer = StatusManage.getPlayer(req.getParameter("target_player"));
			
			StatusManage.getActionLogic().action(player, targetPlayer);
			StatusManage.initActionLogic();
			
		}
		
		//画面制御に使用する列挙型の定数を設定
		req.setAttribute("DEAD", PlayerStatus.DEAD);
		req.setAttribute("ALIVE", PlayerStatus.ALIVE);
		req.setAttribute("WEREWOLF", RoleName.WEREWOLF);
		req.setAttribute("昼", Phase.昼);
		req.setAttribute("夜", Phase.夜);
				
		//表示するメッセージを設定
		req.setAttribute("message", StatusManage.getMessage());
		
		//表示するメッセージを設定
		req.setAttribute("result", StatusManage.getGameResultMessage());
		
		//ターンを設定
		req.setAttribute("turn", StatusManage.getTurn());
				
		//昼または夜の設定
		req.setAttribute("phase", StatusManage.getPhase());

		RequestDispatcher rd = null;
		
		//メイン画面に遷移
		rd = req.getRequestDispatcher("/view/main.jsp");
		
		//死亡フラグがonならゲームオーバー画面に遷移
		if (player.getPlayerStatus() == PlayerStatus.DEAD )
			rd = req.getRequestDispatcher("/view/gameover.jsp");
		
		//ゲーム結果メッセージが空欄でない場合は、結果画面に遷移
		if (StatusManage.getGameResultMessage() != null && StatusManage.getGameResultMessage().length() != 0) 
			rd = req.getRequestDispatcher("/view/result.jsp");
		
		rd.forward(req, res);
		
	}

}
