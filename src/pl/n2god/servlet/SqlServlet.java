package pl.n2god.servlet;

import pl.n2god.data.City;
import pl.n2god.db.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author n2god on 16/12/2019
 * @project jdbcEeExercise
 */
@WebServlet(name = "SqlServlet", urlPatterns = "/SqlServlet")
public class SqlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("get");
		String paramSorted = request.getParameter("getSorted");
		if ("show".equals(param)) {
			getList(request, response, false);
		}
		else if ("show".equals(paramSorted)) {
			getList(request, response, true);
		}
		else {
			response.sendError(403);
		}
	}

	private void getList(HttpServletRequest request, HttpServletResponse response, boolean isSorted) throws ServletException, IOException {
		try {
			List<City> cityList = getCities();
			if (isSorted){
				cityList.sort(Comparator.comparingInt(City::getPopulation).reversed());
			}
			request.setAttribute("cityList", cityList);
			request.getRequestDispatcher("cityList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500); //nie udało się pobrać danych
		}
	}

	private List<City> getCities() throws SQLException {
		List<City> cityList = null;
		final String sqlQuery = "SELECT Name, Population FROM city";

		try (Connection connection = DbUtil.getInstance().getConnection();
		     Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(sqlQuery);) {
			String cityName = null;
			int cityPopulation = 0;
			cityList = new ArrayList<>();
			while (resultSet.next()) {
				cityName = resultSet.getString("Name");
				cityPopulation = resultSet.getInt("Population");
				City city = new City(cityName, cityPopulation);
				cityList.add(city);
			}
			return cityList;
		}
	}

	@Override
	public void destroy() {
		DbUtil.getInstance().close();
	}
}
