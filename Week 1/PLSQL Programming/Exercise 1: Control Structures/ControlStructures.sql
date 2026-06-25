-- Scenario 1

BEGIN
    FOR rec IN (SELECT LoanID, InterestRate
                FROM Loans l
                JOIN Customers c
                ON l.CustomerID = c.CustomerID
                WHERE MONTHS_BETWEEN(SYSDATE, c.DOB)/12 > 60)
    LOOP
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE LoanID = rec.LoanID;
    END LOOP;

    COMMIT;
END;
/

-- Scenario 2

ALTER TABLE Customers ADD IsVIP VARCHAR2(5);

BEGIN
    FOR rec IN (SELECT CustomerID
                FROM Customers
                WHERE Balance > 10000)
    LOOP
        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = rec.CustomerID;
    END LOOP;

    COMMIT;
END;
/

-- Scenario 3

BEGIN
    FOR rec IN (
        SELECT c.Name, l.EndDate
        FROM Customers c
        JOIN Loans l
        ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: ' || rec.Name ||
            ' loan due on ' || rec.EndDate
        );
    END LOOP;
END;
/