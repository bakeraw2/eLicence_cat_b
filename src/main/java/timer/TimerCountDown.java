package timer;

import ui.exam.logic.ExamPresenter;

import java.util.TimerTask;

/**
 * Author: Daniel
 * Date: 09.11.13
 */
public abstract class TimerCountDown extends TimerTask {
    private int sec;
    private ExamPresenter examPresenter;

    public TimerCountDown(ExamPresenter examPresenter, int sec) {
        this.sec = sec;
        this.examPresenter = examPresenter;
        this.examPresenter.showWaitMedia();
    }

    @Override
    public void run() {
        if (sec > 0) {
            if (sec == 15) {
                examPresenter.showMedia();
            }
            examPresenter.getTimerLbl().setText("" + sec);
        } else {
            examPresenter.cancelTimerCountdownTask();
            examPresenter.trySaveAnswer();
            examPresenter.nextQuestion();
        }

        sec--;
    }
}