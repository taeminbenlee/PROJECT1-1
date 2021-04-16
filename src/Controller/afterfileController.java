package Controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import Dao.afterStoryDao;
import Dto.afterStoryDto;


@WebServlet("/afterfile") 
public class afterfileController extends HttpServlet{
	
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	// 업로드한 경로를 취득하기 위해서
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doImageWrite(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doImageWrite(req, resp);
	}

	public void doImageWrite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
		
			System.out.println("In doImageWriteController");
			
			String param = req.getParameter("param");
			
			if(param.equals("afterstoryAf")) {
				System.out.println("afterstoryAf");

				
				String fupload = mConfig.getServletContext().getRealPath("/upload"); // 저장 위치
				System.out.println("업로드 경로 : " + fupload );

				
				int yourMaxRequestSize = 100 * 1024 * 1024;	// 파일 최대 사이즈 (1 Mb)
				int yourMaxMemorySize = 100 * 1024;			// 메모리 사이즈 (1 Kb)
		
				// 셋팅
				String myid = "";
				String filename = "";
				String title = "";
				String mycontent = "";
				
		
	

				//넘어온게  multipart인지 검사
				boolean isMultipart = ServletFileUpload.isMultipartContent(req);
				if(isMultipart == true){

				// FileItem 객채 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				System.out.println("afterWriteController 1");

				// File 셋팅 및 업로드
				factory.setSizeThreshold(yourMaxMemorySize);  // 파일 사이즈 
				factory.setRepository(new File(fupload)); // 저장소 
				ServletFileUpload upload = new ServletFileUpload(factory); //펙토리에서의 값을 저장
				upload.setSizeMax(yourMaxRequestSize); //메모리사이즈 셋팅
				System.out.println("afterWriteController 2");


				//list에 정보 저장후 꺼내기
				List<FileItem> items = null;
				try {
					items = upload.parseRequest(req);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				System.out.println("afterWriteController 3");

				Iterator<FileItem> it = items.iterator();
				System.out.println("afterWriteController 4");

				
				//데이터 타입 조사
				while(it.hasNext()){
					FileItem item = it.next();

			
					if(item.isFormField()){	
						
						if(item.getFieldName().equals("id")){
							myid = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("title")){
							title = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("content")){
							mycontent = item.getString("utf-8");
						}
						
						else if(item.getFieldName().equals("filename")){
							filename = item.getString("utf-8");
						}

					}				
					
					else{	
						if(item.getFieldName().equals("fileload")){

							//파일위치+파일명
							filename = item.getName(); 

							//파일을 다시 업로드 하는 경우
							if(!(filename == null || filename.equals(""))){
								int lastInNum = filename.lastIndexOf(".");
								String exName = filename.substring(lastInNum);
								
								//이미지 파일 아니라면
								if(!(exName.equals(".jpg") || exName.equals(".png")  
								   || exName.equals(".gif")  || exName.equals(".jpeg") || exName.equals(".jfif"))) {
									resp.sendRedirect("after?param=fail");
									return;
								}
								
								//새로운 파일명 + 확장자
								filename = (new Date().getTime()) + ""; 
								filename = filename + exName;
								
								//파일 업로드
								processUploadFile(item, fupload, filename); 
								
								//확인하기 
								System.out.println("id : "+myid);
								System.out.println("title : "+title);
								System.out.println("content : "+mycontent);
								System.out.println("filename : "+filename);
															
				
								}
							} 	
						} 
				}
				
				
				//DB에 저장
				afterStoryDao dao = afterStoryDao.getInstance();
				afterStoryDto dto = new afterStoryDto(myid, title, mycontent,filename);
					
				boolean isS = dao.writeStory(dto);
				if(isS)
					System.out.println("write Success!!");
				
				resp.sendRedirect("after?param=afterstoryList");
				
				}		
			}	
	
				else if (param.equals("afterUpdate")){
					System.out.println("afterUpdate");
			         String fupload = mConfig.getServletContext().getRealPath("/upload"); //저장 위치
			         
			         int yourMaxRequestSize = 100 * 1024 * 1024;   // 파일 최대 사이즈 (1 Mb)
			         int yourMaxMemorySize = 100 * 1024;         // 메모리 사이즈 (1 Kb)
			         
			         String yourTempDir = fupload;
			         
			         // 변수
			     	String myid = "";
					String filename = "";
					String title = "";
					String mycontent = "";
			        String sseq="";
			        String newfilename = "";
			        String oldfilename="";
			        
			         //넘어온게  multipart인지 검사
			         boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			         if(isMultipart == true){
			   
			            // FileItem 객채 생성
			            DiskFileItemFactory factory = new DiskFileItemFactory();
			            System.out.println("afterUpdate 1");
			   
			            // File 셋팅 및 업로드
			            factory.setSizeThreshold(yourMaxMemorySize);  // 파일 사이즈 
			            factory.setRepository(new File(fupload)); // 저장소 
			            ServletFileUpload upload = new ServletFileUpload(factory); //펙토리에서의 값을 저장
			            upload.setSizeMax(yourMaxRequestSize); //메모리사이즈 셋팅
			            System.out.println("afterUpdate 2");
			   
			   
			            //list에 정보 저장후 꺼내기
			            List<FileItem> items = null;
			            try {
			               items = upload.parseRequest(req);
			            } catch (FileUploadException e) {
			               // TODO Auto-generated catch block
			               e.printStackTrace();
			            } 
			            System.out.println("afterUpdate 3");
			   
			            Iterator<FileItem> it = items.iterator();
			            System.out.println("afterUpdate 4");
			   
			            
			            //데이터 타입 조사
			            while(it.hasNext()){
			               FileItem item = it.next();
			   
			               //폼 필드라면
			               if(item.isFormField()){	
								
								if(item.getFieldName().equals("id")){
									myid = item.getString("utf-8");
								}
								else if(item.getFieldName().equals("title")){
									title = item.getString("utf-8");
								}
								else if(item.getFieldName().equals("content")){
									mycontent = item.getString("utf-8");
								}
								else if(item.getFieldName().equals("oldfile")){
									oldfilename = item.getString("utf-8");
								}
								else if(item.getFieldName().equals("seq")){
									sseq = item.getString("utf-8");
									System.out.println("NULL CHECK SSEQ"  + sseq);
								}
								
			               
			   
			               }            
			               //폼 필드가 아니라면 
			               else{	
								if(item.getFieldName().equals("fileload")){

									//파일위치+파일명
									filename = item.getName(); 

									//파일을 다시 업로드 하는 경우
									if(!(filename == null || filename.equals(""))){
										int lastInNum = filename.lastIndexOf(".");
										String exName = filename.substring(lastInNum);
										
										//새로운 파일명 + 확장자
										filename = (new Date().getTime()) + ""; 
										filename = filename + exName;
										
										newfilename = filename;
										
										//파일 업로드
										processUploadFile(item, fupload, newfilename); 
										
				                        
				                     }
									
				                  }
				      
				               }
					            // 변경된 파일이 없으므로 기존의 파일을 저장한다
									if(filename == null || filename.equals("")){
										filename = oldfilename;
										newfilename = filename;
										
									}
				            
								}
			               } 	
						         afterStoryDao dao = afterStoryDao.getInstance();
						         int seq = Integer.parseInt(sseq);
						         System.out.println("id : "+myid);
						         System.out.println("title : "+title);
						         System.out.println("content : "+mycontent);
						         System.out.println("newfilename : "+newfilename);
						         System.out.println("seq : " + seq);
						         
						         //확인하기 
						         afterStoryDto dto = new afterStoryDto(0, myid, title, mycontent, "0", newfilename, 0, 0);
						         boolean isS = dao.updateStory(seq, dto);
						         if(isS) {
						            resp.sendRedirect("after?param=afterstoryList");
						         }
						         
			            	}
			         }
				
			// 파일 업로드용 Method		
			public String processUploadFile(FileItem fileItem, String dir, String filename)throws IOException{
	
				String originalfilename = fileItem.getName();	// 경로 + 파일명
				long sizeInBytes = fileItem.getSize();          // 파일의 크기
	
				//파일이 정상인지 확인
				if(sizeInBytes > 0){			// d:\\tmp\\abc.txt 혹은 d:/tmp/abc.txt 
					//파일명만 가져오기
					int idx = originalfilename.lastIndexOf("\\"); 
					if(idx == -1){
						idx = originalfilename.lastIndexOf("/");
					}
	
					originalfilename = originalfilename.substring(idx + 1);
					//파일 생성
					File uploadFile = new File(dir, filename); //경로, 파일 이름
	
					//실제 파일에 업로드 (Byte값 저장)
					try{	
						fileItem.write(uploadFile);		
					}catch(Exception e){
						e.printStackTrace();
					}		
				}
				
				System.out.println("fileUpload success!");
				
				return originalfilename;
			
		}
		
		
	
}