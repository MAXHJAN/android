package max.phonebook.db;

public interface BlackListDao {

	/**
	 * 加入黑名单
	 * 
	 * @param number
	 *            电话号码
	 */
	public void addBlackList(String number, String name);

	/**
	 * 移除黑名单
	 * 
	 * @param number
	 *            电话号码
	 */
	public void deleteBlackList(String number);

	/**
	 * 判断来电号码是否在黑名单之中
	 * 
	 * @param number
	 *            电话号码
	 * @return
	 */
	public boolean queryBlack(String number);
}
