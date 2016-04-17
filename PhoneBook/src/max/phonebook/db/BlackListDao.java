package max.phonebook.db;

import java.util.List;

import max.phonebook.Ben.BlackList;

public interface BlackListDao {

	/**
	 * ���������
	 * 
	 * @param number
	 *            �绰����
	 */
	public void addBlackList(String number, String name);

	/**
	 * �Ƴ�������
	 * 
	 * @param number
	 *            �绰����
	 */
	public void deleteBlackList(String number);

	/**
	 * �ж���������Ƿ��ں�����֮��
	 * 
	 * @param number
	 *            �绰����
	 * @return
	 */
	public boolean queryBlack(String number);

	/**
	 * ��ѯ���к�����
	 * 
	 * @return
	 */
	public List<BlackList> queryBlackList();
}
