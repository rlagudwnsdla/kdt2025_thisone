package fffff33;

public class CheckBoxExample {
    public static void main(String[] args) {
        CheckBox checkBox = new CheckBox();
        checkBox.setOnSelectListener(new CheckBox.OnSelectListener() {
            @Override
            public void onSelect() {
                System.out.println("slf");
            }
        });
        checkBox.select();
    }
}
