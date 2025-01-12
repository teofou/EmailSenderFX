package com.github.teofou.emailsenderfx.DAO;


import com.github.teofou.emailsenderfx.DAO.dbutil.DBUtil;
import com.github.teofou.emailsenderfx.model.Receiver;
import com.github.teofou.emailsenderfx.service.ecxeptions.*;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;


import java.io.IOException;
import java.util.*;

public class ReceiverDAOImpl implements IReceiverDAO {

    //private static final ExcelFileHeaders excelFileHeaders
    private Map<String, Integer> headersIndexes = new HashMap<>();
    private static final ReceiverDAOImpl receiverDAO = new ReceiverDAOImpl();

    private ReceiverDAOImpl() {}

    public static ReceiverDAOImpl getInstance() {
        return receiverDAO;
    }

//    @Override
//    public void createOrUpdateConfigExcel(ConfigExcelProperties newConfigExcelProperties) throws FileNotFoundException {
//        PrintWriter pwConfig = null;
//        try {
//            pwConfig = new PrintWriter(new FileOutputStream(ConfigUtil.getConfigExcelSettingsFilePath()));
//        } catch (FileNotFoundException e) {
//            throw e;
//        }
//
//        pwConfig.println("excelFilePath: " + newConfigExcelProperties.getExcelFilePath());
//        pwConfig.println("nameHeader: " + newConfigExcelProperties.getNameHeader());
//        pwConfig.println("emailHeader: " + newConfigExcelProperties.getEmailHeader());
//        pwConfig.println("folderHeader: " + newConfigExcelProperties.getFolderHeader());
//        pwConfig.close();
//
//    }
//
//    @Override
//    public void readConfigExcelInstance() throws IOException, WrongSettingsFileException {
//
//        BufferedReader bfConfig = new BufferedReader(new FileReader(ConfigUtil.getConfigExcelSettingsFilePath()));
//
//        String line = bfConfig.readLine();
//        if (line == null) throw new WrongSettingsFileException();
//        while (line != null) {
//            String[] splitted = line.split(" ", 2);
//            if (splitted.length < 2) {
////                setDefaultSettings(new PrintWriter(new FileOutputStream(ConfigUtil.getConfigFileNamePath())));
//                throw new WrongSettingsFileException();
//            }
//            switch (splitted[0]) {
//                case "excelFilePath:":
//                    ConfigExcelProperties.getInstance().setExcelFilePath(splitted[1]);
//                    break;
//                case "nameHeader:":
//                    ConfigExcelProperties.getInstance().setNameHeader(splitted[1]);
//                    break;
//                case "emailHeader:":
//                    ConfigExcelProperties.getInstance().setEmailHeader(splitted[1]);
//                    break;
//                case "folderHeader:":
//                    ConfigExcelProperties.getInstance().setFolderHeader(splitted[1]);
//                    break;
//                default:
//                    throw new WrongSettingsFileException();
//            }
//            line = bfConfig.readLine();
//        }
//    }

    @Override
    public Receiver getOneReceiver() {
        Receiver receiver = new Receiver();

        return null;


    }

    @Override
    public List<Receiver> getReceivers() throws IOException, ExcelFileIsEmptyException,
            WrongNumberOfCellsException, CellIsNotStringException, WrongHeaderNamesException, EmptyCellException, HeadersDuplicateException, WrongExcelPropertiesException, PropertiesFileNotFoundException, ExcelFileNotFoundException, WrongPropertiesFileException, ReceiversNotFoundException, NotOfficeXmlFileException {
//
//        String excelFilepath =

        Map<String, String> correctExcelHeaders = new HashMap<>();
        String nameHeader;
        String emailHeader;
        String folderHeader;




//        Iterator<String> correctHeaders = Arrays.stream(excelFileHeaders).iterator();
        List<Receiver> receivers = new ArrayList<>();
        Workbook workbook = null;
        Sheet sheet = null;
        Iterator<Row> rowIterator = null;
        Row firstRow = null;






        //Ανοιγμα του αρχειυ, ελεγχος αν ειναι κενο
        try {
            workbook = DBUtil.openExcelFile();
            DBUtil.closeExcelFile();
        } catch (ExcelFileNotFoundException | PropertiesFileNotFoundException | WrongPropertiesFileException | NotOfficeXmlFileException e) {
            throw e;
        }
        sheet = workbook.getSheetAt(0);
        rowIterator = sheet.iterator();
        if (!rowIterator.hasNext()) {
            throw new ExcelFileIsEmptyException();
        }

        try {
            correctExcelHeaders = DBUtil.getCorrectExcelHeaders();
        } catch (WrongPropertiesFileException e) {
            throw e;
        }



        nameHeader = correctExcelHeaders.get("nameHeader");
        emailHeader = correctExcelHeaders.get("emailHeader");
        folderHeader = correctExcelHeaders.get("folderHeader");



        //Ελεγχος πρωτης γραμμης και μετα καταναλωση(σβησιμο) αυτης
        firstRow = sheet.getRow(0);
        try {
            headersIndexes = getHeadersIndexes(firstRow);
        } catch (HeadersDuplicateException | CellIsNotStringException e) {
            throw e;
        }
        if (!headersIndexes.containsKey(nameHeader) || !headersIndexes.containsKey(emailHeader) || !headersIndexes.containsKey(folderHeader)) {
            throw new WrongHeaderNamesException(nameHeader, emailHeader, folderHeader);
        }
        int nameHeaderIndex = headersIndexes.get(nameHeader);
        int folderHeaderIndex = headersIndexes.get(folderHeader);
        int emailHeaderIndex = headersIndexes.get(emailHeader);
        sheet.removeRow(firstRow);




        //Διαβασμα δεδομενων των επομενων σειρων
        for (Row row : sheet) {

            Receiver receiver = new Receiver();

            headersIndexes.forEach((key, value) -> {
                String cellValue = null;
                row.getCell(value, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cellValue =  (row.getCell(value).getCellType() == CellType.STRING) ?  row.getCell(value).getStringCellValue() : new DataFormatter().formatCellValue(row.getCell(value));

                if (key.equals(nameHeader)) {
                    receiver.setName(cellValue);
                } else if (key.equals(emailHeader)) {
                    receiver.setEmail(cellValue);
                } else if (key.equals(folderHeader)) {
                    receiver.setFolder(cellValue);
                }
            });


            if (receiver.getName().equals("")) throw new EmptyCellException(row.getCell(headersIndexes.get(nameHeader)).getAddress());
            receivers.add(receiver);
        }

        if (receivers.size() == 0) throw new ReceiversNotFoundException();
        return receivers;
    }


    private static Map<String, Integer> getHeadersIndexes(Row row) throws HeadersDuplicateException, CellIsNotStringException {

        Map<String, Integer> headersIndexes = new HashMap<>();

        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) throw new CellIsNotStringException(0, cell.getColumnIndex());
            if (headersIndexes.containsKey(cell.getStringCellValue())) {
                throw new HeadersDuplicateException(cell.getStringCellValue());
            }
            headersIndexes.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        return headersIndexes;
    }

}
