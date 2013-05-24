import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ExcelExport {

	private File file;

	public ExcelExport(String filename) throws Exception {
		try {
			file = new File(filename + ".xls");
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e2) {
			throw new Exception("error creating the file: " + filename);
		}
	}

	public void createExcel(TableData data) throws Exception {
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String s : data.getColumnNames())
				bw.write(s + "\t");
			bw.write("\n");
			for (Object[] line : data.getInfo()) {
				for (Object s : line)
					bw.write(s + "\t");
				bw.write("\n");
			}
			bw.write("\n");
			bw.close();
			fw.close();
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
