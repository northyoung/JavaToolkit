/**
 * java 文件操作工具
 * 
 * ls 文件列表
 * cp 拷贝文件
 * rm 删除文件 
 * cd 切换文件目录
 * pwd 打印当前目录
 * 
 * 
 * @author lj.xu 2013-10-24
 */
package filetoolkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileToolkit {
	private String correntPath;

	public FileToolkit() {
		this.correntPath = System.getProperty("user.dir");
	}

	/**
	 * 执行用户命令
	 */
	private void route() {
		String input = this.getUserInput();
		String params [] = input.split(" ");
		
		if (params == null || params.length<=0) {
			this.getUserInput();
		} else if (params[0].trim().equalsIgnoreCase("pwd")) {
			this.pwd();
		} else if (params[0].trim().equalsIgnoreCase("exit")) {
			this.exit();
		} else if (params[0].trim().equalsIgnoreCase("ls")){
			this.ls();
		} else if (params[0].trim().equalsIgnoreCase("touch")){
			this.touch(params);
		} else if (params[0].trim().equalsIgnoreCase("rm")){
			this.rm(params);
		} else if (params[0].trim().equalsIgnoreCase("mkdir")){
			this.mkdir(params);
		} else if (params[0].trim().equalsIgnoreCase("cp")){
			this.cp(params);
		}
		else {
			this.getUserInput();
		}
	}
	/**
	 * 删除
	 */
	private void rm(String [] params){
		if(params.length >= 2){
			File file = new File(params[1]);
			if(file.exists()){
				file.delete();
				System.out.println("delete file "+params[1]+" success");
			}
		}
	}
	/**
	 * 拷贝文件
	 */
	private void cp(String [] params){
		if(params.length >=3 ){
			File file1 = new File(params[1]);
			File file2 = new File(params[2]);
			
			if(!file1.exists()){
				System.out.println(params[1] + " doesn't exists");
				return ;
			}
			
			if(file2.exists()){
				System.out.println(params[2] + " already exists");
				return ;
			}
			
			if(file2.getParent()!=null){
				File parentFile = new File(file2.getParent());
				if(!parentFile.exists()){
					System.out.println(parentFile.getPath()+" doesn't exists");
				}
			}

			InputStream inStream = null;
			FileOutputStream outStream = null;
			try {
				
				inStream = new FileInputStream(file1);
				outStream = new FileOutputStream(file2);

				byte[] buffer = new byte[1024];
				int bytesum = 0;
				int byteread = 0;
				
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					outStream.write(buffer, 0, byteread);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					inStream.close();
					outStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 创建文件  
	 */
	private void touch(String [] params){
		if(params.length >= 2){
			File file = new File(params[1]);
			if(!file.exists()){
				try {
					file.createNewFile();
					System.out.println("create file "+params[1]+" success");
				} catch (IOException e) {
					return;
				}
			}else{
				
			}
		}
	}
	
	/**
	 * 创建文件夹
	 */
	private void mkdir(String [] params){
		if(params.length >= 2){
			File file = new File(params[1]);
			if(!file.exists()&&!file.isDirectory()){
				file.mkdir();
				System.out.println("create folder "+params[1]+" success");
			}else{
				
			}
		}
	}
	
	/**
	 * 退出
	 */
	private void exit() {
		System.out.println("Bye Bye!");
		System.exit(0);
	}

	/**
	 * 文件列表
	 */
	private void ls() {
		File file = new File(this.correntPath);
		if (file.isDirectory()) {
			File[] t = file.listFiles();
			for (int i = 0; i < t.length; i++) {
				// 判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为止
				if (t[i].isDirectory()) {
					System.out.println(t[i].getName() + "\t\t\tdir");
				} else {
					System.out.println(t[i].getName() + "\t\t\tFile");
				}
			}
		}
	}

	/**
	 * 打印当前路径
	 */
	private void pwd() {
		System.out.println(this.correntPath);
	}

	/**
	 * 打印欢迎信息
	 */
	private void printfWelcomeMsg() {
		System.out.println("============欢迎使用 java 文件管理客户端=============");
		System.out.println("pwd   打印当前目录");
		System.out.println("rm    删除文件");
		System.out.println("exit  退出");
	}

	/**
	 * 获取用户输入信息
	 * 
	 * @return
	 */
	private String getUserInput() {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String rootDir = null;
		try {
			rootDir = br.readLine().trim();
		} catch (IOException e) {
			
		} finally {
			return rootDir;
		}
	}

	public static void main(String[] agrs) {

		FileToolkit ftl = new FileToolkit();
		ftl.printfWelcomeMsg();

		while (true) {
			ftl.route();
		}
	}
}
