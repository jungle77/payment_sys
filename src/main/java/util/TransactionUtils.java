package util;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jungle77.paymentsys.domain.Transaction;
import com.jungle77.paymentsys.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionUtils {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleTaskUsingCronExpression() {
      
        log.info("deleting transactions older them one hour");
        
        Timestamp now = new Timestamp(System.currentTimeMillis() - 60 * 60 * 1000);
        
        List<Transaction> transactionsToDelete = transactionRepository.findByCreateDateLessThan(now);
        
        transactionRepository.deleteAll(transactionsToDelete);
        
        List<String> ids = transactionsToDelete.stream().map(t -> t.getId()).collect(Collectors.toList());
        
        log.info("deleted transactions older them one hour: {}", String.join("", ids));
        
    }
    
    
}
