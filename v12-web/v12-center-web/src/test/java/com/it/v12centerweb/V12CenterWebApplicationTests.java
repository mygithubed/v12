package com.it.v12centerweb;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12CenterWebApplicationTests {

	@Autowired
	private FastFileStorageClient client;

	@Test
	public void contextLoads() throws IOException{

		File f = new File("");
		FileInputStream inputStream = new FileInputStream(f);

		StorePath d =  client.uploadFile(inputStream,f.length(),"html",null);

		System.out.println(d.getFullPath());
	}

	@Test
	public void delet(){
		client.deleteFile("group1/M00/00/00/wKg0gl0DxG2APc2sAAACyc06vw029.html");
		System.out.println("删除成功");
	}

}
