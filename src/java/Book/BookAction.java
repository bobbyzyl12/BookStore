package Book;

import Book.Book.Book;
import User.User.User;
import User.UserService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class BookAction extends ActionSupport {
    private BookService service;
    public BookAction() {
    }
    
    public void setService(BookService service)
    {
        this.service = service;
    }
    
    public String SearchBooks()
    {
        String type = ServletActionContext.getRequest().getParameter("type");
        String search = ServletActionContext.getRequest().getParameter("search");
        List<Book> books = service.SearchBook(type, search);
        ServletActionContext.getRequest().setAttribute("booklist", books);
        HttpSession session = ServletActionContext.getRequest().getSession();  
        if(session.getAttribute("managerName")!=null){
            return "manager";
        }  
        else{
            return "user";
        }
    }
    
    public String AddBook()
    {
        Book book = new Book();
        book.setBookname(ServletActionContext.getRequest().getParameter("bookname"));
        book.setAuthor(ServletActionContext.getRequest().getParameter("author"));
        Double price = Double.parseDouble(ServletActionContext.getRequest().getParameter("price"));
        
        book.setPrice(price);
        book.setCategory(ServletActionContext.getRequest().getParameter("category"));
       
        if(service.AddBook(book))
            return SUCCESS;
        return ERROR;
    }
    
    public String JumpBooks()
    {
       /*
        //注册WebService接口  
        factory.setServiceClass(BookSOAP.class);  
        //设置WebService地址  
        factory.setAddress("http://localhost:8080/BookStore/webservice/BookSOAP");  
        BookSOAP bookSoap = (BookSOAP)factory.create();  
        return bookSoap.test(1);*/
        return SUCCESS;
    }
    
    public void TestAjax() throws IOException
    {
         System.out.println("test ajax");
         HttpServletRequest request = ServletActionContext.getRequest();
         String bookid = request.getParameter("bookid");
         HttpServletResponse response = ServletActionContext.getResponse();
         response.setContentType("text/html");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write("testing ajax");
    }
    
    public void BookDetail() throws IOException
    {
         HttpServletRequest request = ServletActionContext.getRequest();
         Integer bid = Integer.parseInt(request.getParameter("bookid"));
         Book book = service.SearchBookByID(bid);
         System.out.println("test ajax");
         //respond
         String respondStr = "<a href=\"#\" onclick=\"closeBg();\"><button class=\"btn\" style=\"width:120px;\">close</button></a>";
         if(book!=null){
             respondStr = "<p> id:"+ bid +"</p>"+
                          "<p> Name:"+ book.getBookname() +"</p>"+
                          "<p> Category:"+ book.getCategory() +"</p>"+
                          "<p> Author:"+ book.getAuthor() +"</p>"+
                          "<p> Price:"+ book.getPrice() +"</p>"+
                          respondStr;
         }
         HttpServletResponse response = ServletActionContext.getResponse();
         response.setContentType("text/html");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(respondStr);
    }
    
    
    public String JumpBookDetailM(){
       System.out.println("jumping");
       Integer bid = Integer.parseInt(ServletActionContext.getRequest().getParameter("bid"));
       Book book = service.SearchBookByID(bid);
       ServletActionContext.getRequest().setAttribute("book_search", book);
       return SUCCESS;
    }
    
    public String JumpBookDetail(){
       System.out.println("jumping");
       Integer bid = Integer.parseInt(ServletActionContext.getRequest().getParameter("bid"));
       Book book = service.SearchBookByID(bid);
       ServletActionContext.getRequest().setAttribute("book_search2", book);
       //Integer uid = ((User)ServletActionContext.getRequest().getSession().getAttribute("User")).getId();
       return SUCCESS;
    }
    
    public String JumpLuceneSearch(){
       System.out.println("jumping");
       //Integer uid = ((User)ServletActionContext.getRequest().getSession().getAttribute("User")).getId();
       return SUCCESS;
    }
    
    public String testSOAP(){
        return "1";
    }
   
    public String CreateBookIndex(){
        String indexPath = "D:\\luceneData\\index";
        String docsPath = "D:\\luceneData\\data";
        boolean create = true;

        final Path docDir = Paths.get(docsPath);
        if (!Files.isReadable(docDir)) {
          System.out.println("Document directory '" +docDir.toAbsolutePath()+ "' does not exist or is not readable, please check the path");
          System.exit(1);
        }

        Date start = new Date();
        try {
          System.out.println("Indexing to directory '" + indexPath + "'...");

          Directory dir = FSDirectory.open(Paths.get(indexPath));
          Analyzer analyzer = new StandardAnalyzer();
          IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

          if (create) {
            // Create a new index in the directory, removing any
            // previously indexed documents:
            iwc.setOpenMode(OpenMode.CREATE);
         } else {
            // Add new documents to an existing index:
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
          }

          // Optional: for better indexing performance, if you
          // are indexing many documents, increase the RAM
         // buffer.  But if you do this, increase the max heap
          // size to the JVM (eg add -Xmx512m or -Xmx1g):
          //
          // iwc.setRAMBufferSizeMB(256.0);

          IndexWriter writer = new IndexWriter(dir, iwc);
          indexDocs(writer, docDir);

          // NOTE: if you want to maximize search performance,
          // you can optionally call forceMerge here.  This can be
          // a terribly costly operation, so generally it's only
          // worth it when your index is relatively static (ie
          // you're done adding documents to it):
          //
          // writer.forceMerge(1);

          writer.close();

          Date end = new Date();
          System.out.println(end.getTime() - start.getTime() + " total milliseconds");

        } catch (IOException e) {
          System.out.println(" caught a " + e.getClass() +
           "\n with message: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String LuceneSearch() throws IOException, ParseException{
      String usage =
      "Usage:\tjava org.apache.lucene.demo.SearchFiles [-index dir] [-field f] [-repeat n] [-queries file] [-query string] [-raw] [-paging hitsPerPage]\n\nSee http://lucene.apache.org/core/4_1_0/demo/ for details.";
    String index = "D:\\luceneData\\index";
    String field = "contents";
    String queries = null;
    int repeat = 0;
    boolean raw = false;
    String queryString = ServletActionContext.getRequest().getParameter("search");
    
    int hitsPerPage = 100;
    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
    IndexSearcher searcher = new IndexSearcher(reader);
    Analyzer analyzer = new StandardAnalyzer();

    BufferedReader in = null;
    QueryParser parser = new QueryParser(field, analyzer);

    Query query = parser.parse(queryString);
      
    System.out.println("Searching for: " + query.toString(field));
    searcher.search(query, null, 100);
    //doSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);    
    ServletActionContext.getRequest().setAttribute("booklist", doSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null));
    reader.close();
      return SUCCESS;
  } 
    
    private static void indexDocs(final IndexWriter writer, Path path) throws IOException {
    if (Files.isDirectory(path)) {
      Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          try {
            indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
          } catch (IOException ignore) {
            // don't index files that can't be read.
          }
          return FileVisitResult.CONTINUE;
        }
      });
    } else {
      indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
    }
  }

  /** Indexes a single document */
  static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
    try (InputStream stream = Files.newInputStream(file)) {
      // make a new, empty document
      Document doc = new Document();
      
     // Add the path of the file as a field named "path".  Use a
      // field that is indexed (i.e. searchable), but don't tokenize 
      // the field into separate words and don't index term frequency
      // or positional information:
      Field pathField = new StringField("path", file.toString(), Field.Store.YES);
      doc.add(pathField);
      
      // Add the last modified date of the file a field named "modified".
      // Use a LongField that is indexed (i.e. efficiently filterable with
      // NumericRangeFilter).  This indexes to milli-second resolution, which
      // is often too fine.  You could instead create a number based on
      // year/month/day/hour/minutes/seconds, down the resolution you require.
      // For example the long value 2011021714 would mean
      // February 17, 2011, 2-3 PM.
      doc.add(new LongField("modified", lastModified, Field.Store.NO));
      
      // Add the contents of the file to a field named "contents".  Specify a Reader,
      // so that the text of the file is tokenized and indexed, but not stored.
      // Note that FileReader expects the file to be in UTF-8 encoding.
      // If that's not the case searching for special characters will fail.
      doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
      
      if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
        // New index, so we just add the document (no old document can be there):
        System.out.println("adding " + file);
        writer.addDocument(doc);
      } else {
        // Existing index (an old copy of this document may have been indexed) so 
        // we use updateDocument instead to replace the old one matching the exact 
        // path, if present:
        System.out.println("updating " + file);
        writer.updateDocument(new Term("path", file.toString()), doc);
      }
    }
  }
 
  private List<Book> doSearch(BufferedReader in, IndexSearcher searcher, Query query, 
                                     int hitsPerPage, boolean raw, boolean interactive) throws IOException {
     
    //
    List<Book> booklist = new ArrayList<Book>();
    // Collect enough docs to show 5 pages
    TopDocs results = searcher.search(query, 5 * hitsPerPage);
    ScoreDoc[] hits = results.scoreDocs;
    
    int numTotalHits = results.totalHits;
    System.out.println(numTotalHits + " total matching documents");

    int start = 0;
    int end = Math.min(numTotalHits, hitsPerPage);
      
      for (int i = start; i < end; i++) {
        Document doc = searcher.doc(hits[i].doc);
       String path = doc.get("path");
        if (path != null) {
          System.out.println((i+1) + ". " + path);
          String name = path.trim();  
          String temp[] = name.split("\\\\"); //split里面必须是正则表达式，"\\"的作用是对字符串转义，其中split("\\\\")的作用是：按照"\\"为分隔符，将路径截取，并存入数组，如下：temp[]=[,D:,java,workspace,netmanager01,resources,mibfiles,wtView.mib]  
          name = temp[temp.length-1];//(取出最后一个)
          name = name.substring(0, name.length()-4);
          booklist.addAll(service.SearchBook("bookname", name));
          System.out.println((i+1) + ". " + name);
          
        } else {
          System.out.println((i+1) + ". " + "No path for this document");
        } 
      }
      return booklist;
    } 
    
}
