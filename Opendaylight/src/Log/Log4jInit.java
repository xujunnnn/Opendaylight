package Log;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.*;

/**
 * Servlet implementation class Log4jInit
 */
@WebServlet("/Log4jInit")
public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log4jInit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	    String projectpath=this.getClass().getClassLoader().getResource("/").getPath();
		String log4jPropertiesFilePath = config.getInitParameter("log4j");
		
		/**
		 * 4. 如果获取到配置在servlet初始化参数中的log4j.properties的文件路径.
		 * 我们则用Log4j提供的方法进行配置.
		 */
		if (log4jPropertiesFilePath != null) {
			PropertyConfigurator.configure(projectpath+ log4jPropertiesFilePath);
	
		}
	}


	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
