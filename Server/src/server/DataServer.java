package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controll.CDirectory;
import controll.CInquire;
import controll.CInquireResult;
import controll.CLecture;
import controll.CNotice;
import controll.CResult;
import controll.CStudent;
import model.MDirectory;
import model.MInquire;
import model.MInquireResult;
import model.MLecture;
import model.MLog;
import model.MNotice;
import model.MStudent;
import view.serverMgt.ServerLogTable;



public class DataServer {

	ExecutorService executorService;
	ServerSocket serverSocket;
	List<Client> connections = new Vector<>();
	
	private ServerLogTable serverLogTable;
	private MLog mLog;
	private SimpleDateFormat format;
	
	public DataServer(ServerLogTable serverLogTable) {
		
		format = new SimpleDateFormat ( "yyyy/MM/dd HH:mm:ss");
		this.serverLogTable = serverLogTable;
		
	}

	public void startServer() {
	
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
		} catch (IOException e) {
			if(!serverSocket.isClosed()) {
				stopServer();
			}
//			e.printStackTrace();
			return;
		}
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("[Server Start]");
					
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						String message = "Accept connection: " + socket.getRemoteSocketAddress();
						System.out.println(message);
						
						// Log save
						InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
						String ip = isa.getHostName();
						Calendar time = Calendar.getInstance();						       
						String format_time = format.format(time.getTime());
						String connectionSize = Integer.toString(connections.size() + 1);
						mLog = new MLog(ip, "로그인 서버 접속", format_time, connectionSize);						
						serverLogTable.updateTableContents(mLog);					
						
						Client client = new Client(socket);
						connections.add(client);
						System.out.println("Connection count: " + connections.size());
						
					} catch(IOException e) {
						e.printStackTrace();
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
					
				}
				
			}
		};
		
		
		executorService.submit(runnable);		
		
	}

	public void stopServer() {
		
		try {
			Iterator<Client> iterator = connections.iterator();
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if(executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			System.out.println("Stop Server");
			
		} catch(Exception e) {
			
		}
		
	}
	
	class Client {

		Socket socket;
		InetSocketAddress isa;
		String ip;
		Client(Socket socket) {
			
			this.socket = socket;
			this.isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.ip = isa.getHostName();
			receive();
			
		}
		
		void receive() {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					
					try {
						while(true) {
							byte[] byteArr = new byte[5000];
							InputStream inputStream = socket.getInputStream();
														
							int readByteCount = inputStream.read(byteArr);
							if(readByteCount < 0) {
								throw new IOException();
							}
							
							String originData = new String(byteArr, 0, readByteCount, "UTF-8");
							System.out.println("=====================Server Recive Data=====================");
							System.out.println(originData);
							System.out.println("============================================================");
							String[] dataArr = originData.trim().split(" ");
							System.out.println("here ok0");
							
							String result = "";
							
							if(dataArr[0].equals("login")) {
								
								CStudent cStudent = new CStudent();								
								MStudent mStudent = cStudent.getStudent(dataArr[1], dataArr[2]);
								
								if(mStudent != null) {
									
									result = "loginSuccess";
									send(result + " " + mStudent.toString());
								} else {
									result = "loginFail";
									send(result);
									
								}
								
							} else if(dataArr[0].equals("getDirectorys")) {
								CDirectory cDirectory = new CDirectory();
								Vector<MDirectory> mDirectorys = cDirectory.getDirectories(dataArr[1], dataArr[2]);
								if(mDirectorys.size() > 0) {
									result = "mDirectorys";
									String allData = "";
									for(MDirectory mdirectory : mDirectorys) {
										allData += mdirectory.toString() + "\n";
									}
									send(result + " " + allData);
								}
							
							} else if(dataArr[0].equals("getGangjwas")) {
								
								CLecture cLecture = new CLecture();
								Vector<MLecture> mLectures = cLecture.getLecture(Integer.parseInt(dataArr[1]));
								
								if(mLectures.size() > 0) {
									result = "getGangjwas";
									String allData = "";
									for(MLecture mGamgjwa : mLectures) {
										allData += mGamgjwa.toString() + "\n";
									}
									send(result + " " + allData);
								}
								
							} else if(dataArr[0].equals("basket") || dataArr[0].equals("sincheong")) {
								
								CResult cResult = new CResult();
								Vector<MLecture> mLectures = cResult.getResult(dataArr[1], dataArr[0]);
								
								if(mLectures.size() > 0) {
									result = "getResult";
									String allData = "";
									for(MLecture mGamgjwa : mLectures) {
										allData += mGamgjwa.toString() + "\n";
									}
									send(result + " " + allData);
								} else {
									result = "noResult";
									send(result);
								}
								
							} else if(dataArr[0].equals("save")) {
								CResult cResult = new CResult();
								cResult.save(originData);
								result = "saveSuccess";
								send(result);
							} else if(dataArr[0].equals("delete")) {
								CResult cResult = new CResult();
								cResult.deleteResult(originData);
								result = "deleteSuccess";
								send(result);
							} else if(dataArr[0].equals("getNotice")) {
								CNotice cNotice = new CNotice();
								
								Vector<MNotice> mNotices = cNotice.getNotice();
								
								if(mNotices.size() > 0) {
									result = "notice";
									String allData = "";
									for(MNotice mNotice : mNotices) {
										allData += mNotice.toString();
									}
									
									send(result + " " + allData);
								} else {
									
									send("notice");
								}
								
							} else if(dataArr[0].equals("updateUser")) {
								CStudent cStudent = new CStudent();
								cStudent.updateUser(originData);
								result = "updateUserSuccess";
								send(result);
								
							} else if(dataArr[0].equals("findUser")) {
								CStudent cStudent = new CStudent();
								MStudent mStudent = cStudent.findUser(originData);
								if(mStudent != null) {
									
									result = "findUserSuccess";
									send(result + " " + mStudent.toString());									
								} else {
									result = "findUserFail";
									send(result);
								}
							} else if(dataArr[0].equals("getMyInquire")) {
								CInquire cInquire = new CInquire();
								Vector<MInquire> mInquires = cInquire.getMyInquire(originData);
								
								if(mInquires.size() > 0) {
									result = "getMyInquireSuccess";
									String allData = "";
									for(MInquire mInquire : mInquires) {
										allData += mInquire.toString();
									}
									send(result + " " + allData);
								} else {
									result = "getMyInquireFail";
									send(result);
								} 
								
							} else if(dataArr[0].equals("createInquire")) {
								CInquire cInquire = new CInquire();
								cInquire.createInquire(originData);
								result = "createInquireSuccess";
								send(result);
								
							} else if(dataArr[0].equals("updateInquire")) {
								CInquire cInquire = new CInquire();
								cInquire.updateInquire(originData);
								result = "updateInquireSuccess";
								send(result);
								
							} else if(dataArr[0].equals("getAnswer")) {
								CInquireResult cInquire = new CInquireResult();
								MInquireResult mInquireResult = cInquire.getInquireResult(dataArr[1]);
								if(mInquireResult != null) {
									
									String allData = mInquireResult.toString();
									result = "getAnswerSuccess";
									send(result + " " + allData);
								} else {
									result = "getAnswerFalil";
									send(result);
								}
							}
						}
						
					} catch (Exception e) {
						// 연결이 끊어진 클라이언트를 제거
						try {
							
							// Log save							
							Calendar time = Calendar.getInstance();						       
							String format_time = format.format(time.getTime());
							String connectionSize = Integer.toString(connections.size() - 1);
							mLog = new MLog(ip, "클라이언트 연결 끊김", format_time, connectionSize);						
							serverLogTable.updateTableContents(mLog);
							
							connections.remove(Client.this);
							socket.close();
							
						} catch (IOException e1) {
//							e1.printStackTrace();
						}
					}
				}
			};
			
			executorService.submit(runnable);
		}
		
		void send(String data) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {							
						
						
						// Save Log
						String[] dataArr = data.split(" ");
						if(dataArr[0].equals("loginFail")) {
							
							Calendar time = Calendar.getInstance();						       
							String format_time = format.format(time.getTime());
							String connectionSize = Integer.toString(connections.size());
							mLog = new MLog(ip, "로그인 실패", format_time, connectionSize);						
							serverLogTable.updateTableContents(mLog);
						} else if(dataArr[0].equals("loginSuccess")) {
							
							Calendar time = Calendar.getInstance();						       
							String format_time = format.format(time.getTime());
							String connectionSize = Integer.toString(connections.size());
							mLog = new MLog(ip, "로그인 성공", format_time, connectionSize);						
							serverLogTable.updateTableContents(mLog);
						}											
						
						// Send Data
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(byteArr);
						outputStream.flush();
						
					} catch (Exception e) {												
						// 클라이언트 통신 안 될경우 에러 발생 따라서 제거
						try {
							connections.remove(Client.this);
							socket.close();
						} catch (IOException e1) {
//							e1.printStackTrace();
						}
					}
				}
			};
			
			executorService.submit(runnable);
		}
	}
}
