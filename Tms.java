package com.briup.sms;
import java.util.Scanner;
/**
	学生信息管理系统
*/
public class Tms {
	//学生数组，用于保存所有学生信息
	private Teacher[] ts = new Teacher[3];
	//表示学生数组中真正保存学生的个数
	private int index = 0;

		

	/**
		查找所有学生
		stus = {
			{1001,terry,12},
			{1002,larry,13},
			null
		}
		demo={
			{1001,terry,12},
			{1002,larry,13}
		}
		index = 2
	*/
	public Teacher[] findAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(ts,0,demo,0,index);
		return demo;
	}

	/**
		添加学生
		stus =	demo={
			{1001,terry,12},
			{1001,terry,12},
			{1001,terry,12},
			null,
			null,
			null
		}
		index = 3;
	 */
	public void add(Teacher t){
		if(index>=ts.length){
			//数组扩展！ 6
			Teacher[] demo = new Teacher[ts.length+3];
			System.arraycopy(ts,0,demo,0,index);
			ts = demo;
		}
		ts[index] = t;// stus[3] = stu
		index++;
	}

	/**
		通过id删除学生 
		@author lisi
		@param 学生id
	*/
	public void deleteById(long id){
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			ts[i] = ts[i+1];
		}
		ts[--index] = null;
	}
	/**
		通过id查找学生
		@author zhangsan
		@param 要删除学生的id  : long
		@return 
			学生，找到了
			null， 没找到
	*/
	public Teacher findById(long id){
		int num = findIndexById(id);
		return num == -1?null:ts[num];
	}
	/**
		通过id查找学生在数组中的索引
		@author licy
		@param 要查找学生的id  : long
		@return 
			-1，找到了
			其他正整数， 没找到
	*/
	public int findIndexById(long id){
		int num = -1;
		for(int i=0;i<index;i++){
			if(ts[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}
	
	/**
		修改学生信息
	*/
	public void update(Teacher newt){
		for(int i=0;i<index;i++){
			if(ts[i].getId() == newt.getId()){
				ts[i].setName(newt.getName());
				ts[i].setAge(newt.getAge());
			}
		}
	}

	//菜单
	public void menu(){
		System.out.println("*********教师信息管理系统*********");
		System.out.println("* 1. 查看所有教师信息");
		System.out.println("* 2. 录入教师信息");
		System.out.println("* 3. 删除教师信息");
		System.out.println("* 4. 更新教师信息");
		System.out.println("* 5. 查询教师信息");
		System.out.println("* help. 帮助");
		System.out.println("* exit. 退出");
		System.out.println("**********************************");
	}	
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		//扫描标准输入，等待用户的输入
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			//阻塞，直到用户输入回车，将回车前所有用户输入返回
			String line = sc.nextLine();
			switch(line){
				case "1":
					System.out.println("以下是所有教师的信息：");
					Teacher[] arr = tms.findAll();
					for(Teacher t : arr){
						System.out.println(t);
					}
					System.out.println("共计 "+tms.index+" 人");
					break;
				case "2":
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入【break】返回上一级");
						String tStr = sc.nextLine();
						if(tStr.equals("break")){
							break;
						}
						//1001#terry#12
						String[] tArr = tStr.split("#");
						long id  = Long.parseLong(tArr[0]);//"1001"
						String name = tArr[1];
						int age = Integer.parseInt(tArr[2]);
						Teacher t = new Teacher(id,name,age);
						tms.add(t);
						System.out.println("保存成功！");
					}
					
					break;
				case "3":
					while(true){
						System.out.println("请输入要删除教师的id或者break返回上一级：");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher t = tms.findById(id);
						if(t==null){
							System.out.println("您要删除的教师不存在！");
							continue;	//结束当前循环，继续下一次循环
						}
						tms.deleteById(id);
						System.out.println("删除成功！");
					}
					break;
				case "4":
					while(true){
						System.out.println("请输入要更改教师的id或者break返回上一级：");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher t = tms.findById(id);
						if(t==null){
							System.out.println("您要更改的教师不存在！");
							continue;	//结束当前循环，继续下一次循环
						}
						System.out.println("原有教师信息为：");
						System.out.println(t);
						System.out.println("请输入要修改的信息【name#age】");
						String tStr = sc.nextLine();
						String[] tArr = tStr.split("#");
						String name = tArr[0];
						int age = Integer.parseInt(tArr[1]);
						Teacher newt = new Teacher(id,name,age);
						tms.update(newt);
						System.out.println("修改成功");
					}
					break;
				case "5":
					while(true){
						System.out.println("请输入要查询教师的id或者break返回上一级：");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher t = tms.findById(id);
						if(t==null){
							System.out.println("您要查询的教师不存在！");
							continue;	//结束当前循环，继续下一次循环
						}
						System.out.println(t);
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye!欢迎下次再访问！");
					System.exit(0);
				default:
					System.out.println("输入错误！");
			}
		}
	}	
}