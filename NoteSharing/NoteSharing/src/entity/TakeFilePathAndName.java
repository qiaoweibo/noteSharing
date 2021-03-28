package entity;
import java.io.File;    

/**  
 * 
 * 该类可以输出指定路径下所有的文件名（文件名和文件夹名）
 * 指定一个路径即可
 *  
 */  
public class TakeFilePathAndName {    

    public static void main(String[] args) {   
        //这是需要获取的文件夹路径  
        String path = "C:/Java EE/apache-tomcat-7.0.77/webapps/NoteSharing/images";   
        getFile(path,0);   
    }   
	
    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    private static void getFile(String path,int deep){   
        // 获得指定文件对象  
        File file = new File(path);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();   

        for(int i=0;i<array.length;i++)
        {   
            if(array[i].isFile())//如果是文件
            {   
                    for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字  
                System.out.println( array[i].getName());   
                // 输出当前文件的完整路径   
               // System.out.println("#####" + array[i]);   
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下   
                System.out.println(array[i].getPath());   
            }
            else if(array[i].isDirectory())//如果是文件夹
            {  
                    for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

                    System.out.println( array[i].getName());
                    //System.out.println(array[i].getPath());
                    //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(),deep+1);  
            }
            //System.out.println("TakeFilePathAndName.getFile()");
        }   
    }   
} 