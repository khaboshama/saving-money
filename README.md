# Saving Money

# Project description

This application is Android application which will be used for anyone to control his/her budget, accounts, transactions, payees, and setting goal for saving. I used MVVM design pattern to divide the application into three parts, model, view and view model.

The first screen displays the list of budgets using recyclerview.
The second screen displays the list of accounts of selected budget using recyclerview.
The third screen displays the form of adding new account.

The application divided into some packages.

# constants package

It contains the static and constants variables of the app.

# model package

It contains the models of the app.

# network package

It contains the classes which related to the end points, and responses and retrofit.

# utils package

It contains general files and classes for using them in the application.

# ui package

It contains the packages of the application screens. For each package, it divided into three packages adapter, view and view_model.

# test using Espresso

You can run UI test using Espresso by opening the BudgetListInstrumentedTest class, right click on editor screen, then click run button.