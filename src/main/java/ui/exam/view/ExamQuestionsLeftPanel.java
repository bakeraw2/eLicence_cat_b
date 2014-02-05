package ui.exam.view;

import database.dao.TextsDao;
import org.apache.log4j.Logger;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import util.ApplicationUtils;
import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamQuestionsLeftPanel extends JPanel {
    private Logger logger = ApplicationUtils.getLogger(ExamQuestionsLeftPanel.class);

    private Canvas component;
    private EmbeddedMediaPlayer player;

    private JPanel abcBtnPanel;
    private JPanel yesNoBtnPanel;

    private JTextArea questionTextArea;

    private JTextArea yesBtn;
    private JTextArea noBtn;
    private JTextArea btnA;
    private JTextArea btnB;
    private JTextArea btnC;

    private Color defaultColor;

    public ExamQuestionsLeftPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        try {
            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
            component = new Canvas();
            player = mediaPlayerFactory.newEmbeddedMediaPlayer();
            player.setVideoSurface(mediaPlayerFactory.newVideoSurface(component));

            Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

            setLayout(null);
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
            setAlignmentX(Component.LEFT_ALIGNMENT);
            setBorder(emptyBorder);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private void initializeComponents() {
        JPanel imagePanel = getImagePanel();
        JPanel questionPanel = getQuestionPanel();
        abcBtnPanel = getABCBtnPanel();
        yesNoBtnPanel = getYesNoBtnPanel();

        add(imagePanel);
        add(questionPanel);
        add(yesNoBtnPanel);
    }

    public JPanel getImagePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        imagePanel.setBounds(10, 10, 640, 357);
        imagePanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(component);

        return imagePanel;
    }

    public JPanel getQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionPanel.setBounds(0, 380, 680, 80);

        questionTextArea = getQuestionTextArea();

        questionPanel.add(questionTextArea);
        return questionPanel;
    }

    private JTextArea getQuestionTextArea() {
        JTextArea questionTextArea = new JTextArea(3, 49);
        questionTextArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        questionTextArea.setFont(Const.Fonts.TEXTS_FONT);
        questionTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setFocusable(false);
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 570, 680, 60);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        yesBtn = createYesNoBtn(TextsDao.getText("yesButtonLbl"));
        noBtn = createYesNoBtn(TextsDao.getText("noButtonLbl"));

        defaultColor = noBtn.getBackground();

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        return buttonPanel;
    }

    private JPanel getABCBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 475, 680, 160);
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        btnA = createABCBtn();
        btnB = createABCBtn();
        btnC = createABCBtn();

        buttonPanel.add(btnA);
        buttonPanel.add(btnB);
        buttonPanel.add(btnC);

        return buttonPanel;
    }

    private JTextArea createYesNoBtn(String label) {
        JTextArea textArea = new JTextArea(label);
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setFont(Const.Fonts.BTNS_YES_NO_FONT);
        textArea.setPreferredSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        textArea.setMinimumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        textArea.setMaximumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);

        textArea.setFocusable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Border compoundBorder = BorderFactory.createLineBorder(Const.Colors.BTN_BORDER_COLOR, 1, true);
        textArea.setBorder(BorderFactory.createCompoundBorder(compoundBorder,
                BorderFactory.createEmptyBorder(20, 150, 10, 10)));
        return textArea;
    }

    private JTextArea createABCBtn() {
        JTextArea textArea = new JTextArea();
        textArea.setFont(Const.Fonts.BTNS_ABC_FONT);
        textArea.setPreferredSize(Const.Dimensions.ABC_BTNS_SIZE);
        textArea.setMinimumSize(Const.Dimensions.ABC_BTNS_SIZE);
        textArea.setMaximumSize(Const.Dimensions.ABC_BTNS_SIZE);

        textArea.setFocusable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Border compoundBorder = BorderFactory.createLineBorder(Const.Colors.BTN_BORDER_COLOR, 1, true);
        textArea.setBorder(BorderFactory.createCompoundBorder(compoundBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return textArea;
    }

    public void setQestion(String qestion) {
        questionTextArea.setText(qestion);
    }

    public void setBtnABCTexts(String btnAText, String btnBText, String btnCText) {
        this.btnA.setText(btnAText);
        this.btnB.setText(btnBText);
        this.btnC.setText(btnCText);
    }

    public void changePanelFromStandarToSpecial() {
        remove(yesNoBtnPanel);
        add(abcBtnPanel);
        repaint();
    }

    public void showWaitImageImage() {
        ApplicationUtils.prepareAndPlayMedia(player, "wait_photo");
    }

    public void showWaitVideoImage() {
        ApplicationUtils.prepareAndPlayMedia(player, "wait_video");
    }

    public void setImageName(String mediaTitle) {
        ApplicationUtils.prepareAndPlayMedia(player, mediaTitle);
    }

    public void setVideoName(String mediaTitle) {
        ApplicationUtils.prepareAndPlayMedia(player, mediaTitle);
    }

    public void enableAllBtns() {
        yesBtn.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        noBtn.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        btnA.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        btnB.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        btnC.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
    }

    public JTextArea getYesBtn() {
        return yesBtn;
    }

    public JTextArea getNoBtn() {
        return noBtn;
    }

    public JTextArea getBtnA() {
        return btnA;
    }

    public JTextArea getBtnB() {
        return btnB;
    }

    public JTextArea getBtnC() {
        return btnC;
    }
}
