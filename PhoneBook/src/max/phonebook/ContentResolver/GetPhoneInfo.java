package max.phonebook.ContentResolver;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import max.phonebook.Ben.CharacterParser;
import max.phonebook.Ben.Person;

public class GetPhoneInfo {

	private Context context;
	/*
	*//** 获取库Phon表字段 **//*
						 * private static final String[] PHONES_PROJECTION = new
						 * String[] { Phone.DISPLAY_NAME, Phone.NUMBER,
						 * Photo.PHOTO_ID,Phone.CONTACT_ID };
						 */

	/** 联系人显示名称 **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;

	/** 电话号码 **/
	private static final int PHONES_NUMBER_INDEX = 1;

	/** 头像ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;

	/** 联系人的ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;

	static ArrayList<Person> list = new ArrayList<Person>();// 创造存放数据的列表
	static ArrayList<Person> list1;// 创造存放数据的列表
	// 获取汉字的首字母
	private CharacterParser characterParser;

	static ArrayList<Person> listph = new ArrayList<Person>();// 创建一个排好序以后的数组（）

	public GetPhoneInfo(Context context) {
		this.context = context;
		characterParser = CharacterParser.getInstance();
	}

	/**
	 * 获取手机联系人
	 * 
	 * @param str=""
	 * @return
	 */
	public List<Person> getphoneinfo(String str) {

		if (str.length() == 0) {
			// ---------------------------------
			// 获取 本地 联系人
			Uri uri = ContactsContract.Data.CONTENT_URI;// 2.0以上系统使用ContactsContract.Data访问联系人
			Cursor cursor = context.getContentResolver().query(uri, null, null, null, "display_name");// 显示联系人时按显示名字排序
			cursor.moveToFirst();
			ArrayList<Person> list = new ArrayList<Person>();
			int Index_CONTACT_ID = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID);// 获得CONTACT_ID在ContactsContract.Data中的列数
			int Index_DATA1 = cursor.getColumnIndex(ContactsContract.Data.DATA1);// 获得DATA1在ContactsContract.Data中的列数
			int Index_MIMETYPE = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE);// 获得MIMETYPE在ContactsContract.Data中的列数
			while (cursor.getCount() > cursor.getPosition()) {
				Person person = null;
				String id = cursor.getString(Index_CONTACT_ID);// 获得CONTACT_ID列的内容
				String info = cursor.getString(Index_DATA1);// 获得DATA1列的内容
				String mimeType = cursor.getString(Index_MIMETYPE);// 获得MIMETYPE列的内容
				// 遍历查询当前行对应的联系人信息是否已添加到list中
				for (int n = 0; n < list.size(); n++) {
					if (list.get(n).getID() != null) {
						if (list.get(n).getID().equals(id)) {
							person = list.get(n);
							break;
						}
					}
				}
				if (person == null) {
					person = new Person();
					person.setID(id);
					list.add(person);
				}
				if (mimeType.equals("vnd.android.cursor.item/email_v2"))// 该行数据为邮箱
				{
					person.setEmail(info);
				} else if (mimeType.equals("vnd.android.cursor.item/postal-address_v2"))// 该行数据为地址
				{
					person.setAddress(info);
				} else if (mimeType.equals("vnd.android.cursor.item/phone_v2"))// 该行数据为电话号码
				{
					person.setPhonenumber(info);
				} else if (mimeType.equals("vnd.android.cursor.item/name"))// 该行数据为名字
				{
					// android.util.Log.e("名字", info);//将联系人的名字打印出来
					person.setName(info);
					person.setSortLetters(getdata(info));
				}
				cursor.moveToNext();
			}

			// ----------------------做一些相对的处理
			// 获得联系人
			android.util.Log.i("联系人的数量", list.size() + "");
			list1 = list;
			// 根据a-z进行排序源数据
			return list1;
		}
		return list1;// 将联系人的信息返回了

	}

	/**
	 * 转为大写字母
	 * 
	 * @param date
	 * @return
	 */

	private String getdata(String str) {
		String pinyin = characterParser.getSelling(str);
		String sortString = pinyin.substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortString.matches("[A-Z]")) {
			return sortString.toUpperCase();

		} else {

			return "#";

		}
	}

	/**
	 * 添加联系人 数据一个表一个表的添加，每次都调用insert方法
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 */
	public void testAddContacts(String name, String phone, String email, String address) {
		/* 往 raw_contacts 中添加数据，并获取添加的id号 */
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		long contactId = ContentUris.parseId(resolver.insert(uri, values));

		/* 往 data 中添加数据（要根据前面获取的id号） */
		// 添加姓名
		uri = Uri.parse("content://com.android.contacts/data");
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/name");
		values.put("data2", name);
		resolver.insert(uri, values);

		// 添加电话
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		values.put("data2", "2");
		values.put("data1", phone);
		resolver.insert(uri, values);

		// 添加Email
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		values.put("data2", "2");
		values.put("data1", email);
		resolver.insert(uri, values);

		// 添加Email
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
		values.put("data2", "2");
		values.put("data1", address);
		resolver.insert(uri, values);
	}

	/**
	 * 更新联系人
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 * @param ContactId
	 */
	public void testUpdate(String name, String phone, String email, String address, String ContactId) {
		Uri uri = Uri.parse("content://com.android.contacts/data");// 对data表的所有数据操作
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put("data1", name);
		resolver.update(uri, values, "mimetype=? and raw_contact_id=?",
				new String[] { "vnd.android.cursor.item/name", ContactId });
		values.clear();
		values.put("data1", phone);
		resolver.update(uri, values, "mimetype=? and raw_contact_id=?",
				new String[] { "vnd.android.cursor.item/phone_v2", ContactId });
		values.clear();
		values.put("data1", email);
		resolver.update(uri, values, "mimetype=? and raw_contact_id=?",
				new String[] { "vnd.android.cursor.item/email_v2", ContactId });
		values.clear();
		values.put("data1", address);
		resolver.update(uri, values, "mimetype=? and raw_contact_id=?",
				new String[] { "vnd.android.cursor.item/postal-address_v2", ContactId });
	}

	/**
	 * 删除联系人
	 * 
	 * @param ContactId
	 */

	public void deleteContact(int ContactId) {
		context.getContentResolver().delete(ContentUris.withAppendedId(RawContacts.CONTENT_URI, ContactId), null, null);
	}

}
