module jmp.bank.application {
    uses com.epam.jmp.dmytro_hontar.bank_api.Bank;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
}