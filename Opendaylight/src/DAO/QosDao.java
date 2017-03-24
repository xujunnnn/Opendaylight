package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import Qos.QosTask;

public interface QosDao  {
	public void Insert(QosTask qosTask) throws SQLException;
	public void update(QosTask qosTask) throws SQLException;
	public void delete(long qosid) throws SQLException;
	public ArrayList<QosTask> querry() throws SQLException;
	public QosTask querryFromId(long qosid) throws SQLException;

}
