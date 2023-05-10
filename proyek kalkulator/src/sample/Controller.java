package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField bil1;

    @FXML
    private TextField bil2;

    @FXML
    private Button button;

    @FXML
    private TextField operate;

    @FXML
    private TextField result;

    @FXML
    private TextField temp;


    DecimalFormat df = new DecimalFormat("#.###");
    ArrayList<Double> bilangan = new ArrayList<>();
    ArrayList<Character> op = new ArrayList<>();
    ArrayList<TextField> arr = new ArrayList<>();

    String tmp = "";
    double tmp2 = 0;
    double hasil = 0;
    int count = 0;

    public void buttonOperate(ActionEvent event) {
        button = (Button) event.getSource();
        String operation = button.getText();
        switch (operation) {
            case "+":
            case "x":
            case "/": {
                this.count++;
                temp = arr.get(count);
                if (tmp2 != 0) {
                    bilangan.add(tmp2);
                    tmp = "";
                    tmp2 = 0;
                }
                temp.setText(operation);
                op.add(button.getText().charAt(0));
                this.count++;
                break;

            }
            case "-": {
                this.count++;
                temp = arr.get(count);
                if (tmp2 != 0 || tmp.equals("")) {
                    bilangan.add(tmp2);
                    tmp = "";
                    tmp2 = 0;
                }
                temp.setText(operation);
                op.add(button.getText().charAt(0));
                this.count++;
                break;
            }

            case "Del": {
                bil2.setText("");
                bil1.setText("");
                operate.setText("");
                result.setText("");
                count = 0;
                tmp2 = 0;
                tmp = "";
                bilangan.clear();
                operate.clear();
                arr.clear();

                bil2.setText("");
                bil1.setText("");
                operate.setText("");
                result.setText("");
                count = 0;
                tmp2 = 0;
                tmp = "";
                bilangan.clear();
                operate.clear();
                arr.clear();
                break;
            }
            case "C": {
                if (count == 0) {
                    temp = arr.get(count);
                    temp.setText("");
                    tmp2 = 0;
                    tmp = "";
                    break;
                } else if (count == 2) {
                    if (temp.getText().charAt(0) == '+' || temp.getText().charAt(0) == '-' || temp.getText().charAt(0) == 'x' ||temp.getText().charAt(0) == '/') {
                        temp = arr.get(count - 1);
                        temp.setText("");
                        tmp2 = 0;
                        tmp = "";
                        op.clear();
                        count-=2;
                        break;
                    }
                    else if (count == 2 && arr.get(count).getText().charAt(0) != '+' || count == 2 && arr.get(count).getText().charAt(0) != '-' || count == 2 && arr.get(count).getText().charAt(0) != '/' || count == 2 && arr.get(count).getText().charAt(0) != 'x') {
                        temp = arr.get(2);
                        temp.setText("");
                        tmp2 = 0;
                        tmp = "";
                        break;
                    }

                } else {
                    result.setText("ERROR");
                    break;
                }
            }

            case "+/-": {
                tmp2 *= -1;
                if (op.size() == 0) {
                    temp.setText(String.valueOf(tmp2));

                } else if (op.size() == 1) {
                    temp.setText(String.valueOf(tmp2));
                } else if (op.size() == 2) {
                    temp.setText(String.valueOf(tmp2));
                }

                tmp = String.valueOf(tmp2);
                break;

            }
            case "=": {
                this.count++;
                if (tmp2 != 0) bilangan.add(tmp2);
                if (count == 3) {
                    temp = arr.get(count);

                    tmp = "0";
                    button = (Button) event.getSource();
                    temp.setText(temp.getText() + button.getText());
                    tmp2 = 0;
                    temp.setText("");
                    getResult(bilangan, op.get(0));

                    for (Double a : bilangan) System.out.println(a);
                    for (Character b : op) System.out.println(b);
                    break;

                } else {
                    count--;
                    break;
                }

            }
        }
    }

    @FXML
    public void buttonAngka(ActionEvent event) {
        if (arr.size() == 0) {
            arr.add(bil1);
            arr.add(operate);
            arr.add(bil2);
            arr.add(result);
        }
            temp = arr.get(count);
            button = (Button) event.getSource();
            temp.setText(temp.getText() + button.getText());
            tmp += button.getText();
            tmp2 = Double.parseDouble(tmp);
    }

    public void getResult(ArrayList<Double> bilangan, char op) {
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        if (bilangan.size() == 2) {
            if (op == '+') {
                hasil = bilangan.get(0) + bilangan.get(1);
            } else if (op == '-') {
                hasil = bilangan.get(0) - bilangan.get(1);
            } else if (op == 'x') {
                hasil = bilangan.get(0) * bilangan.get(1);
            } else if (op == '/') {
                hasil = bilangan.get(0) / bilangan.get(1);
            }
            System.out.println(hasil);

            if (hasil % 1 == 0) temp.setText(Integer.toString((int) hasil));
            else {
                String res = df.format(hasil);
                temp.setText(res);

            }
        }
    }

}

