module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    provides com.epam.jmp.dmytro_hontar.bank_api.Bank with com.epam.jmp.dmytro_hontar.cloud_bank_impl.CloudyBankFactory;
}