package com.alipay.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.demo.trade.config.Configs;
@WebServlet("/alipaydemo")
public class alipaydemo extends HttpServlet {
	static {
		 Configs.init("zfbsandbox.properties");
	}
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String APP_ID="2016092300574520";
		String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjgH+/AhlmKFL8GYVNuCKRYON7Yy6TrkV68gmicIzdEVB9U842VGnhVZppfxJh4GRz5Nut7DJW3z9GOKeOVMk85Qik9tMRW03mHyzkdypfCZe/CqdPni4pn3hkP20dg+UWC0DjnjrircHugpKdpn57yx/wGtdZL9+BL2Vxz18cYSsyoWBiHrXygN/fEhRW23JuklfPkA2gn2CUf7b9X48qDStEju6uc9S3EiNoY3HD9rynA+6Zsh5trCRwPXyJjt27bndXssoWbCKSc+GLXMyO/KoVyCFKuCcZIsWM/11fVIGlnkJuhkmq12WJL5fBpjCD6qC16J1v+MmZ7F/uX76ZAgMBAAECggEAFhOWrCLLbSjKs7F6d1kCieCsNo/YzSX9ybD+rglGa98XoCR9toZFWaCmXdJDmCWlSqS/KMe4sFQqhf/GWAiIt4kxVpzhOPm8FfNlq1+PwA9ahvgYST7RettGdTq/L/oMJ6LTWyiVSOAvTFYjF9hQQcTWhiQJqcg2BIpyxyA59mnMT3aHxDlpvm39TrMRPIw7YUb0p+EHdJb5koC0UrrJnfrAuizg+1/w9+d7vvcitY1aQve6glvPOmfahvazVWlU23Udfk13tQArcd1STT5XMDzocuTf132PrgWu/6LeVJIkIAv1SMJH1+fY8uHkc4ZJNuyQPxaV0Z0vOhNOvDp7EQKBgQDyhwPzeMHLHSUSPGZcis7b9TpPJ/ZUU6D60KqKRcT9xIQV5oRFrVCWE2WoOcDz8zj3FePyXsobLj/Z9UYwAttRoHXBtUbenkj/d9EdCxbPht+Mil0Q263zGjxj+7lLsA8C4271bNqmCjKIKIziLaWhEy5zvLHNPC5sRN2io3l2BQKBgQCslalst1aNved76u03iIrW8Z/W7dno0IZzJUoOYt14/JFACnmLK6NHNQENnKiErki6IV6L83C/Oj4BUnUp+xEZOyvUdij51Xfq5HT+7EddUoS43+JKqK9yAR30Z+/UixEN3nJhJl22tz9EdlZpZNNDhb6Z0Uu7dDAsWLdZEG0WhQKBgQDwXrShyJt57ebiDg79xW2EP6aM8XSI22H/dHSyGrhFM1IeSpvqWv413/+vHZ6JqhRZf86uVxhEnDAYm6n2x74LRtevJzWYSpTa7ivJd0YiZPCHYFMg9DmRaf1UsxZRV6tpF3TPthV230B6BoarXMZ9wH8XonsuiGkSUabBHPiy6QKBgDWfAlbe16uOVz+vDCj26XxC8lCK8etd7ogUC+BQY1NV1aM6He1Zj49uLneWNt2ScNIzz/ZXmq4vYFh00CZM9VELi2ClnbGR24JFnPwfWUTrgIUYOmzL4EbhhfcL2/4Dfzb56Du6+d7tLGEPC2YPh3wxL7kGyCrjvJvqrHyll98pAoGASKM0hFDEpfBwACELibD3TuEuX4KyepvlhlGFc+Z6KkLkgg17Cclj4XqH0CsPYlD7p7ig7raHV9khKgJ6iQRGlcrH6jdC8tURssV+sS6I6tmVZwJ9APF4Rzl3UqEKR6dxsrYP1SHAQaq4sc2H3xUSE5TLByiQXHDEWmAwLJgf0pA=";
		String FORMAT="json";
		String CHARSET="utf-8";
		String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzoj0LShFwsROuMw/NjIaIVWXRNllexHX+s7F6loKYMt1kI7wyEf6MygSxjTuYDfx78ExewykXt/d/9JHdUeQuAx2i0z+rIqLFCtOvA1DYKn2v+mWhe/bY8yJhVIGeDP8ALU/sGTg64ShRf5I52EjSIKC782vaFrPA+05Rdmlz4JOqOZzxmhG4beGJpXjpAHMFzg3Pq8tr5pINSWPNRiQ7q5Hhc4jyhKL4naSQlaxQTtUBM+eSfBCA9gHAdrEe2ucuBTK1z/Uh/sHGpiZMzpeBexTe/oEaAi9JtYNr4jJZCYZbz/O1JV9TSbhFXnJ/3rZxB7t8T/mVGLKtBLWi0/QXQIDAQAB";
		String SIGN_TYPE="RSA2";
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
		alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent("{" +
		  "    \"out_trade_no\":\"20180320010101001\"," +
		  "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		  "    \"total_amount\":9800," +
		  "    \"subject\":\"小王零售店\"," +
		  "    \"body\":\"IphoneXS MAX 256G\"," +
		  "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
		  "    \"extend_params\":{" +
		  "    \"sys_service_provider_id\":\"2088511833207846\"" +
		  "    }"+
		  "  }");//填充业务参数
		String form="";
		try {
		  form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		} catch (AlipayApiException e) {
		  e.printStackTrace();
		}
		response.setContentType("text/html;charset=" + CHARSET);
		response.getWriter().write(form);//直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
