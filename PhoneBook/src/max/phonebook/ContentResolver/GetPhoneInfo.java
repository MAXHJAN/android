package max.phonebook.ContentResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.TextUtils;
import max.phonebook.Ben.CharacterParser;
import max.phonebook.Ben.Person;
import max.phonebook.Ben.PinyinComparator;

public class GetPhoneInfo {

	private Context context;
	
	/**��ȡ��Phon���ֶ�**/  
	private static final String[] PHONES_PROJECTION = new String[] {  
		Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID };  
	
	/**��ϵ����ʾ����**/  
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;  

	/**�绰����**/  
	private static final int PHONES_NUMBER_INDEX = 1;  

	/**ͷ��ID**/  
	private static final int PHONES_PHOTO_ID_INDEX = 2;  

	/**��ϵ�˵�ID**/  
	private static final int PHONES_CONTACT_ID_INDEX = 3; 

	static ArrayList<Person> list=new ArrayList<Person>();//���������ݵ��б�
	static ArrayList<Person> list1=new ArrayList<Person>();//���������ݵ��б�
	//��ȡ���ֵ�����ĸ
	private CharacterParser cp=new CharacterParser();

	private CharacterParser characterParser;

	static ArrayList<Person> listph=new ArrayList<Person>();//����һ���ź����Ժ�����飨��
	
	private PinyinComparator pinyinComparator;
	 
	
	
	public GetPhoneInfo(Context context)
	{
		this.context=context;
		characterParser = CharacterParser.getInstance();
	}
	
	public List<Person> getphoneinfo(String str)
	{


		if(str.length()==0){
			//���в�ѯ
			//----------------------------------  ��ѯSIM��
			ContentResolver resolver =context.getContentResolver();  
			// ��ȡSims����ϵ��  
			Uri uri1 = Uri.parse("content://icc/adn");  
			Cursor phoneCursor = resolver.query(uri1, PHONES_PROJECTION, null, null,  
					null);  
			if (phoneCursor != null) {  
				while (phoneCursor.moveToNext()) {  
					Person mperson=new Person();//����һ������
					// �õ��ֻ�����  
					String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);  
					// ���ֻ�����Ϊ�յĻ���Ϊ���ֶ� ������ǰѭ��  
					if (TextUtils.isEmpty(phoneNumber))  
						continue;  
					// �õ���ϵ������  
					String contactName = phoneCursor  
							.getString(PHONES_DISPLAY_NAME_INDEX);  

					//Sim����û����ϵ��ͷ��  

					mperson.setName(contactName);//�����ַ���
					mperson.setSortLetters(getdata(contactName));
					mperson.setPhonenumber(phoneNumber);//���绰�����������
					list.add(mperson);//����Ϣȫ���Ž�ȥ 
				}  

				phoneCursor.close();  
			}  
			android.util.Log.e("SIM���е���ϵ�˵ĸ�����",""+ list.size());
			//---------------------------------
			//��ȡ  ���� ��ϵ��
			Uri uri=ContactsContract.Data.CONTENT_URI;//2.0����ϵͳʹ��ContactsContract.Data������ϵ��
			Cursor cursor=context.getContentResolver().query(uri, null, null, null, "display_name");//��ʾ��ϵ��ʱ����ʾ��������    	
			cursor.moveToFirst();
			List<Person> list=new ArrayList<Person>();
			int Index_CONTACT_ID = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID);//���CONTACT_ID��ContactsContract.Data�е�����
			int Index_DATA1 = cursor.getColumnIndex(ContactsContract.Data.DATA1);//���DATA1��ContactsContract.Data�е�����
			int Index_MIMETYPE = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE);//���MIMETYPE��ContactsContract.Data�е�����
			while(cursor.getCount()>cursor.getPosition())
			{
				Person person = null;
				String id=cursor.getString(Index_CONTACT_ID);//���CONTACT_ID�е�����
				String info=cursor.getString(Index_DATA1);//���DATA1�е�����
				String mimeType=cursor.getString(Index_MIMETYPE);//���MIMETYPE�е�����
				//������ѯ��ǰ�ж�Ӧ����ϵ����Ϣ�Ƿ�����ӵ�list��
				for(int n = 0; n<list.size(); n++)
				{
					if(list.get(n).getID() != null)
					{
						if(list.get(n).getID().equals(id))
						{
							person = list.get(n);
							break;
						}
					}
				}
				if(person == null)
				{
					person=new Person();
					person.setID(id);
					list.add(person);
				}
				if(mimeType.equals("vnd.android.cursor.item/email_v2"))//��������Ϊ����
				{
					person.setEmail(info);
				}
				else if(mimeType.equals("vnd.android.cursor.item/postal-address_v2"))//��������Ϊ��ַ
				{
					person.setAddress(info);
				}
				else if(mimeType.equals("vnd.android.cursor.item/phone_v2"))//��������Ϊ�绰����
				{
					person.setPhonenumber(info);
				}
				else if(mimeType.equals("vnd.android.cursor.item/name"))//��������Ϊ����
				{
					//android.util.Log.e("����", info);//����ϵ�˵����ִ�ӡ����
					person.setName(info);
					person.setSortLetters(getdata(info));
				}
				cursor.moveToNext();
			}

			//----------------------��һЩ��ԵĴ���		
			//�����ϵ��
			android.util.Log.i("��ϵ�˵�����",list.size()+"");
			list1.addAll(list);
			pinyinComparator = new PinyinComparator();
			// ����a-z��������Դ����
					Collections.sort(list1, pinyinComparator);
			return list1;
		}
		return list1;//����ϵ�˵���Ϣ������		
	
	}
	
	
	
	
	/**
	 * תΪ��д��ĸ
	 * @param date
	 * @return
	 */
	
	private String getdata(String str)
	{
		String pinyin = characterParser.getSelling(str);
		String sortString = pinyin.substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if(sortString.matches("[A-Z]"))
		{
			return sortString.toUpperCase();
					
		}else{

           return"#";
					
		}
	}
	
	
	
	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * @param filterStr
	 */
	public List<Person> filterData(List<Person>PhoneInfos,String filterStr){
		List<Person> filterDateList = new ArrayList<Person>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = PhoneInfos;
		}else{
			filterDateList.clear();
			for(Person sortModel : PhoneInfos){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		//adapter.updateListView(filterDateList);
		
		return filterDateList;
	}
}
