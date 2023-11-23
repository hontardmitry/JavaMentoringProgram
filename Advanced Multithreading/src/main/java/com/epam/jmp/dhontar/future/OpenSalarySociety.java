package com.epam.jmp.dhontar.future;

import com.epam.jmp.dhontar.future.dto.Employee;
import com.epam.jmp.dhontar.future.service.EmployeeService;
import com.epam.jmp.dhontar.future.service.SalaryService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class OpenSalarySociety {
    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    public OpenSalarySociety(EmployeeService employeeService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.salaryService = salaryService;
    }

    public CompletableFuture<Void> printHiredEmployeesWithSalaries() {
        CompletableFuture<List<CompletableFuture<Employee>>> employeesWithSalariesFuture =
                CompletableFuture.supplyAsync(employeeService::getHiredEmployees)
                        .thenApplyAsync(hiredEmployees ->
                                hiredEmployees.stream()
                                        .map(this::setEmplWithRetrievedSalary)
                                        .collect(Collectors.toList())
                        );

        CompletableFuture<List<Employee>> employeesFuture = employeesWithSalariesFuture.thenApplyAsync(futures ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        );
        return employeesFuture.thenAcceptAsync(employees ->
                employees.forEach(empl ->
                        System.out.println(empl.getName() + ", " + empl.getPosition() + " - " + empl.getSalary()))
        );

    }

    private CompletableFuture<Employee> setEmplWithRetrievedSalary(Employee empl) {
        return CompletableFuture.supplyAsync(() ->
                        salaryService.getSalary(empl.getId()))
                .thenApplyAsync(salary -> {
                    empl.setSalary(salary);
                    return empl;
                });
    }

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        SalaryService salaryService = new SalaryService();
        OpenSalarySociety openSalarySociety = new OpenSalarySociety(employeeService, salaryService);

        long start = System.currentTimeMillis();
        openSalarySociety.printHiredEmployeesWithSalaries().join();
        System.out.println("Time spent: " + (System.currentTimeMillis() - start));
    }
}
