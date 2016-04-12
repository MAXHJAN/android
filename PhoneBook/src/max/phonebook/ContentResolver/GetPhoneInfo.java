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
	*//** ��ȡ��Phon���ֶ� **//*
						 * private static final String[] PHONES_PROJECTION = new
						 * String[] { Phone.DISPLAY_NAME, Phone.NUMBER,
						 * Photo.PHOTO_ID,Phone.CONTACT_ID };
						 */

	/** ��ϵ����ʾ���� **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;

	/** �绰���� **/
	private static final int PHONES_NUMBER_INDEX = 1;

	/** ͷ��ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;

	/** ��ϵ�˵�ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;

	static ArrayList<Person> list = new ArrayList<Person>();// ���������ݵ��б�
	static ArrayList<Person> list1;// ���������ݵ��б�
	// ��ȡ���ֵ�����ĸ
	private CharacterParser characterParser;

	static ArrayList<Person> listph = new ArrayList<Person>();// ����һ���ź����Ժ�����飨��

	public GetPhoneInfo(Context context) {
		this.context = context;
		characterParser = CharacterParser.getInstance();
	}

	/**
	 * ��ȡ�ֻ���ϵ��
	 * 
	 * @param str=""
	 * @return
	 */
	public List<Person> getphoneinfo(String str) {

		if (str.length() == 0) {
			// ---------------------------------
			// ��ȡ ���� ��ϵ��
			Uri uri = ContactsContract.Data.CONTENT_URI;// 2.0����ϵͳʹ��ContactsContract.Data������ϵ��
			Cursor cursor = context.getContentResolver().query(uri, null, null, null, "display_name");// ��ʾ��ϵ��ʱ����ʾ��������
			cursor.moveToFirst();
			ArrayList<Person> list = new ArrayList<Person>();
			int Index_CONTACT_ID = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID);// ���CONTACT_ID��ContactsContract.Data�е�����
			int Index_DATA1 = cursor.getColumnIndex(ContactsContract.Data.DATA1);// ���DATA1��ContactsContract.Data�е�����
			int Index_MIMETYPE = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE);// ���MIMETYPE��ContactsContract.Data�е�����
			while (cursor.getCount() > cursor.getPosition()) {
				Person person = null;
				String id = cursor.getString(Index_CONTACT_ID);// ���CONTACT_ID�е�����
				String info = cursor.getString(Index_DATA1);// ���DATA1�е�����
				String mimeType = cursor.getString(Index_MIMETYPE);// ���MIMETYPE�е�����
				// ������ѯ��ǰ�ж�Ӧ����ϵ����Ϣ�Ƿ�����ӵ�list��
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
				if (mimeType.equals("vnd.android.cursor.item/email_v2"))// ��������Ϊ����
				{
					person.setEmail(info);
				} else if (mimeType.equals("vnd.android.cursor.item/postal-address_v2"))// ��������Ϊ��ַ
				{
					person.setAddress(info);
				} else if (mimeType.equals("vnd.android.cursor.item/phone_v2"))// ��������Ϊ�绰����
				{
					person.setPhonenumber(info);
				} else if (mimeType.equals("vnd.android.cursor.item/name"))// ��������Ϊ����
				{
					// android.util.Log.e("����", info);//����ϵ�˵����ִ�ӡ����
					person.setName(info);
					person.setSortLetters(getdata(info));
				}
				cursor.moveToNext();
			}

			// ----------------------��һЩ��ԵĴ���
			// �����ϵ��
			android.util.Log.i("��ϵ�˵�����", list.size() + "");
			list1 = list;
			// ����a-z��������Դ����
			return list1;
		}
		return list1;// ����ϵ�˵���Ϣ������

	}

	/**
	 * תΪ��д��ĸ
	 * 
	 * @param date
	 * @return
	 */

	private String getdata(String str) {
		String pinyin = characterParser.getSelling(str);
		String sortString = pinyin.substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortString.matches("[A-Z]")) {
			return sortString.toUpperCase();

		} else {

			return "#";

		}
	}

	/**
	 * �����ϵ�� ����һ����һ�������ӣ�ÿ�ζ�����insert����
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 */
	public void testAddContacts(String name, String phone, String email, String address) {
		/* �� raw_contacts ��������ݣ�����ȡ��ӵ�id�� */
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		long contactId = ContentUris.parseId(resolver.insert(uri, values));

		/* �� data ��������ݣ�Ҫ����ǰ���ȡ��id�ţ� */
		// �������
		uri = Uri.parse("content://com.android.contacts/data");
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/name");
		values.put("data2", name);
		resolver.insert(uri, values);

		// ��ӵ绰
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		values.put("data2", "2");
		values.put("data1", phone);
		resolver.insert(uri, values);

		// ���Email
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		values.put("data2", "2");
		values.put("data1", email);
		resolver.insert(uri, values);

		// ���Email
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
		values.put("data2", "2");
		values.put("data1", address);
		resolver.insert(uri, values);
	}

	/**
	 * ������ϵ��
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 * @param ContactId
	 */
	public void testUpdate(String name, String phone, String email, String address, String ContactId) {
		Uri uri = Uri.parse("content://com.android.contacts/data");// ��data����������ݲ���
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
	 * ɾ����ϵ��
	 * 
	 * @param ContactId
	 */

	public void deleteContact(int ContactId) {
		context.getContentResolver().delete(ContentUris.withAppendedId(RawContacts.CONTENT_URI, ContactId), null, null);
	}

}
