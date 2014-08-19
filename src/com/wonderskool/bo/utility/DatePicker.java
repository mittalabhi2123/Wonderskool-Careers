package com.wonderskool.bo.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatePicker
{
  int month = Calendar.getInstance().get(2);
  int year = Calendar.getInstance().get(1);
  JLabel l = new JLabel("", 0);
  String day = "";
  JDialog d;
  JButton[] button = new JButton[49];

  public DatePicker() {
    this.d = new JDialog();
    this.d.setModal(true);
    String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
    JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(430, 120));

    for (int x = 0; x < this.button.length; x++) {
      final int selection = x;
      this.button[x] = new JButton();
      this.button[x].setFocusPainted(false);
      this.button[x].setBackground(Color.white);
      if (x > 6)
        this.button[x].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            DatePicker.this.day = DatePicker.this.button[selection].getActionCommand();
            DatePicker.this.d.dispose();
          }
        });
      if (x < 7) {
        this.button[x].setText(header[x]);
        this.button[x].setForeground(Color.red);
      }
      p1.add(this.button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 3));
    JButton previous = new JButton("<< Previous");
    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        DatePicker.this.month -= 1;
        DatePicker.this.displayDate();
      }
    });
    p2.add(previous);
    p2.add(this.l);
    JButton next = new JButton("Next >>");
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        DatePicker.this.month += 1;
        DatePicker.this.displayDate();
      }
    });
    p2.add(next);
    this.d.add(p1, "Center");
    this.d.add(p2, "South");
    this.d.pack();
    this.d.setLocationRelativeTo(null);
    displayDate();
    this.d.setVisible(true);
  }

  public void displayDate() {
    for (int x = 7; x < this.button.length; x++)
      this.button[x].setText("");
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");

    Calendar cal = Calendar.getInstance();
    cal.set(this.year, this.month, 1);
    int dayOfWeek = cal.get(7);
    int daysInMonth = cal.getActualMaximum(5);
    int x = 6 + dayOfWeek; for (int day = 1; day <= daysInMonth; day++) {
      this.button[x].setText("" + day);

      x++;
    }
    this.l.setText(sdf.format(cal.getTime()));
    this.d.setTitle("Date Picker");
  }

  public String setPickedDate() {
    if (this.day.equals(""))
      return this.day;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    Calendar cal = Calendar.getInstance();
    cal.set(this.year, this.month, Integer.parseInt(this.day));
    return sdf.format(cal.getTime());
  }
}