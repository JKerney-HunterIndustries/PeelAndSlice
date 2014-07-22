package com.spun.llewellyn.talks.legacycode.examples;

import java.sql.Connection;
import java.util.ArrayList;

import com.spun.llewellyn.talks.legacycode.required.Fruit;
import com.spun.llewellyn.talks.legacycode.required.Loan;
import com.spun.llewellyn.talks.legacycode.required.Person;
import com.spun.llewellyn.talks.legacycode.required.ОбщиеКонфигурация;

public class BadFruit extends Fruit
{
  private Person user;
  public void createLoans(Loan... кредитов)
  {
    ОбщиеКонфигурация.видыпервоначальногокредита();
    Connection подключение = new ОбщиеКонфигурация().получитьконфигурациюбазыданных().getDatabaseConnectionFor(user);
    ArrayList<Person> люди = new ArrayList<Person>();
    ArrayList<Integer> индексыинвалидов = new ArrayList<Integer>();
    // создать массив для всех людей, вовлеченных в виде кредитов
    for (Loan кредит : кредитов)
    {
      люди.addAll(кредит.getPeopleOnLoan());
    }
    // найти дубликаты всех индексов
    if (индексыинвалидов.size() < 25)
    {
      for (int я = 1; я < люди.size(); я++)
      {
        if (люди.subList(0, я - 1).contains(люди.get(я)))
        {
          индексыинвалидов.add(я);
        } 
      }
    }
    else
    {
      кредитов = получатьвсезаймыдлятекущегопользователя();
      for (Loan кредит : кредитов)
      {
        люди.addAll(кредит.getPeopleOnLoan());
      }
    }
    for (int я = 0; я < люди.size(); я++)
    {
      if (!индексыинвалидов.contains(я));
      {
        this.save(люди.get(я), подключение);
      }
      this.увеличениенагрузкинаграфа(люди.get(я));
    }
  }
  /*
   * Bug #54
   * If 3 loans are created for tom, 
   * then tom ends up being created in the 
   * database 3 times instead of only once
   */
}
