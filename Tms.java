package com.briup.sms;
import java.util.Scanner;
/**
	ѧ����Ϣ����ϵͳ
*/
public class Tms {
	//ѧ�����飬���ڱ�������ѧ����Ϣ
	private Teacher[] ts = new Teacher[3];
	//��ʾѧ����������������ѧ���ĸ���
	private int index = 0;

		

	/**
		��������ѧ��
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
		���ѧ��
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
			//������չ�� 6
			Teacher[] demo = new Teacher[ts.length+3];
			System.arraycopy(ts,0,demo,0,index);
			ts = demo;
		}
		ts[index] = t;// stus[3] = stu
		index++;
	}

	/**
		ͨ��idɾ��ѧ�� 
		@author lisi
		@param ѧ��id
	*/
	public void deleteById(long id){
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			ts[i] = ts[i+1];
		}
		ts[--index] = null;
	}
	/**
		ͨ��id����ѧ��
		@author zhangsan
		@param Ҫɾ��ѧ����id  : long
		@return 
			ѧ�����ҵ���
			null�� û�ҵ�
	*/
	public Teacher findById(long id){
		int num = findIndexById(id);
		return num == -1?null:ts[num];
	}
	/**
		ͨ��id����ѧ���������е�����
		@author licy
		@param Ҫ����ѧ����id  : long
		@return 
			-1���ҵ���
			������������ û�ҵ�
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
		�޸�ѧ����Ϣ
	*/
	public void update(Teacher newt){
		for(int i=0;i<index;i++){
			if(ts[i].getId() == newt.getId()){
				ts[i].setName(newt.getName());
				ts[i].setAge(newt.getAge());
			}
		}
	}

	//�˵�
	public void menu(){
		System.out.println("*********��ʦ��Ϣ����ϵͳ*********");
		System.out.println("* 1. �鿴���н�ʦ��Ϣ");
		System.out.println("* 2. ¼���ʦ��Ϣ");
		System.out.println("* 3. ɾ����ʦ��Ϣ");
		System.out.println("* 4. ���½�ʦ��Ϣ");
		System.out.println("* 5. ��ѯ��ʦ��Ϣ");
		System.out.println("* help. ����");
		System.out.println("* exit. �˳�");
		System.out.println("**********************************");
	}	
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		//ɨ���׼���룬�ȴ��û�������
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			//������ֱ���û�����س������س�ǰ�����û����뷵��
			String line = sc.nextLine();
			switch(line){
				case "1":
					System.out.println("���������н�ʦ����Ϣ��");
					Teacher[] arr = tms.findAll();
					for(Teacher t : arr){
						System.out.println(t);
					}
					System.out.println("���� "+tms.index+" ��");
					break;
				case "2":
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age���������롾break��������һ��");
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
						System.out.println("����ɹ���");
					}
					
					break;
				case "3":
					while(true){
						System.out.println("������Ҫɾ����ʦ��id����break������һ����");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher t = tms.findById(id);
						if(t==null){
							System.out.println("��Ҫɾ���Ľ�ʦ�����ڣ�");
							continue;	//������ǰѭ����������һ��ѭ��
						}
						tms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}
					break;
				case "4":
					while(true){
						System.out.println("������Ҫ���Ľ�ʦ��id����break������һ����");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher t = tms.findById(id);
						if(t==null){
							System.out.println("��Ҫ���ĵĽ�ʦ�����ڣ�");
							continue;	//������ǰѭ����������һ��ѭ��
						}
						System.out.println("ԭ�н�ʦ��ϢΪ��");
						System.out.println(t);
						System.out.println("������Ҫ�޸ĵ���Ϣ��name#age��");
						String tStr = sc.nextLine();
						String[] tArr = tStr.split("#");
						String name = tArr[0];
						int age = Integer.parseInt(tArr[1]);
						Teacher newt = new Teacher(id,name,age);
						tms.update(newt);
						System.out.println("�޸ĳɹ�");
					}
					break;
				case "5":
					while(true){
						System.out.println("������Ҫ��ѯ��ʦ��id����break������һ����");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher t = tms.findById(id);
						if(t==null){
							System.out.println("��Ҫ��ѯ�Ľ�ʦ�����ڣ�");
							continue;	//������ǰѭ����������һ��ѭ��
						}
						System.out.println(t);
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye!��ӭ�´��ٷ��ʣ�");
					System.exit(0);
				default:
					System.out.println("�������");
			}
		}
	}	
}