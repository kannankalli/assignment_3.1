package com.bigdata.acadgild;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ListDirFilesMultiplePathsRecursively {

	public static void main(String[] args) {
	
		Configuration con = new Configuration();
		try {
			URI hadoopURI = null;
			for ( String arg : args )
			{
				System.out.println("arg is "+arg);
				hadoopURI = URI.create(arg);
				System.out.println("HaoopURI of is "+ hadoopURI.toString());
				Path path = new Path(hadoopURI);
				listFiles(path, con);	
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
		
	public static void listFiles( Path path, Configuration con ) throws IOException {
		
		FileSystem fileSystem = FileSystem.get(path.toUri(), con);
		FileStatus[] fileStatuses = fileSystem.listStatus(path);
		System.out.println("Path ****"+path.getName());
		System.out.println("size of the files **"+fileStatuses.length);
		for ( FileStatus lfs : fileStatuses )
		{
			if ( lfs.isFile() )
			{
				System.out.println("File ** "+lfs.getPath().getName());
			}
			else
			{
				System.out.println("Directory ***"+lfs.getPath().getName());
				listFiles(lfs.getPath(), con);
			}
		}
		
	}

}
