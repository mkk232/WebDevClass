package com.koreait.board2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board2.model.BoardVO;

public class BoardDAO {

	public static BoardVO selBoard(final BoardVO param) {
		BoardVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT i_board, title, ctnt, r_dt " + " FROM t_board_? " + " WHERE i_board = ? ";
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getTyp());
			ps.setInt(2, param.getI_board());
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new BoardVO();
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				String r_dt = rs.getString("r_dt");

				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setCtnt(ctnt);
				vo.setR_dt(r_dt);
				vo.setTyp(param.getTyp());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return vo;
	}

	public static List<BoardVO> selBoardList(final BoardVO param) {
		List<BoardVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT i_board, title, r_dt " + " FROM t_board_? " + " ORDER BY i_board DESC ";
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getTyp());
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();

				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
//				String ctnt = rs.getNString("ctnt"); 리스트 가져올땐 가져오면 트래픽 너무 잡아먹음
				String r_dt = rs.getString("r_dt");

				vo.setI_board(i_board);
				vo.setTitle(title);
//				vo.setCtnt(ctnt);
				vo.setR_dt(r_dt);

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps, rs);
		}

		return list;
	}

	public static int insBoard(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " INSERT INTO t_board_? " + " ( title, ctnt )" + " VALUES " + " ( ?, ? ) ";

		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);

			ps.setInt(1, param.getTyp());
			ps.setNString(2, param.getTitle());
			ps.setNString(3, param.getCtnt());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps);
		}

		return result;
	}
	public static int delBoard(final BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_board_? where i_board = ? ";
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getTyp());
			ps.setInt(2, param.getI_board());
			
			
			result = ps.executeUpdate(); 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps);
		}
		return result;
	}
}








































