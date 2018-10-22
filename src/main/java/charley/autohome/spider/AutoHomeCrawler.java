package charley.autohome.spider;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 功能描述
 *
 * @author Charley
 * @since 1.0
 */
public class AutoHomeCrawler extends WebCrawler {

  private final static String TARGET = "https://www.autohome.com.cn";
  private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");

  @Override
  public boolean shouldVisit(Page referringPage, WebURL url) {
    String href = url.getURL().toLowerCase();
    return !FILTERS.matcher(href).matches() && href.startsWith(TARGET);
  }

  @Override
  public void visit(Page page) {
    String url = page.getWebURL().getURL();  // 获取url
    System.out.println("URL: " + url);

    if (page.getParseData() instanceof HtmlParseData) {  // 判断是否是html数据
      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
      String text = htmlParseData.getText();  // 获取页面纯文本（无html标签）
      String html = htmlParseData.getHtml();  // 获取页面Html
      Set<WebURL> links = htmlParseData.getOutgoingUrls();  // 获取页面输出链接

      System.out.println("纯文本长度: " + text.length());
      System.out.println("html长度: " + html.length());
      System.out.println("输出链接个数: " + links.size());
    }
  }
}
