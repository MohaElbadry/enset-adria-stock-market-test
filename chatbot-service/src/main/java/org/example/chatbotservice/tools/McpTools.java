package org.example.chatbotservice.tools;

import org.example.chatbotservice.clients.CompanyClient;
import org.example.chatbotservice.clients.StockClient;
import org.example.chatbotservice.dto.CompanyRequest;
import org.example.chatbotservice.dto.CompanyResponse;
import org.example.chatbotservice.dto.StockRequest;
import org.example.chatbotservice.dto.StockResponse;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class McpTools {

    private final CompanyClient companyClient;
    private final StockClient stockClient;

    public McpTools(CompanyClient companyClient, StockClient stockClient) {
        this.companyClient = companyClient;
        this.stockClient = stockClient;
    }

    // Company tools
    @McpTool(name = "get_companies", description = "List all companies")
    public List<CompanyResponse> getCompanies() {
        return companyClient.findAll();
    }

    @McpTool(name = "get_company_by_id", description = "Get a company by id")
    public CompanyResponse getCompanyById(@McpArg(description = "Company id") Long id) {
        return companyClient.findById(id);
    }

    @McpTool(name = "get_companies_by_sector", description = "Get companies filtered by sector")
    public List<CompanyResponse> getCompaniesBySector(@McpArg(description = "Sector name") String sector) {
        return companyClient.findBySector(sector);
    }

    @McpTool(name = "create_company", description = "Create a new company")
    public CompanyResponse createCompany(
            @McpArg(description = "Name") String name,
            @McpArg(description = "Listing date ISO-8601 (yyyy-MM-dd)") String listingDate,
            @McpArg(description = "Initial current stock price") Double currentStockPrice,
            @McpArg(description = "Sector") String sector) {
        LocalDate ld = listingDate != null ? LocalDate.parse(listingDate) : null;
        return companyClient.create(new CompanyRequest(name, ld, currentStockPrice, sector));
    }

    @McpTool(name = "update_company_price", description = "Update a company's current price")
    public CompanyResponse updateCompanyPrice(
            @McpArg(description = "Company id") Long id,
            @McpArg(description = "New price") Double value) {
        return companyClient.updatePrice(id, value);
    }

    // Stock tools
    @McpTool(name = "get_stocks", description = "List all stock quotations")
    public List<StockResponse> getStocks() {
        return stockClient.findAll();
    }

    @McpTool(name = "get_stock_by_id", description = "Get a stock quotation by id")
    public StockResponse getStockById(@McpArg(description = "Stock id") Long id) {
        return stockClient.findById(id);
    }

    @McpTool(name = "get_stocks_by_company", description = "List all quotations for a company")
    public List<StockResponse> getStocksByCompany(@McpArg(description = "Company id") Long companyId) {
        return stockClient.findByCompany(companyId);
    }

    @McpTool(name = "create_stock", description = "Create a new stock quotation")
    public StockResponse createStock(
            @McpArg(description = "ISO-8601 datetime (yyyy-MM-dd'T'HH:mm:ss)") String date,
            @McpArg(description = "Open value") Double openValue,
            @McpArg(description = "High value") Double highValue,
            @McpArg(description = "Low value") Double lowValue,
            @McpArg(description = "Close value") Double closeValue,
            @McpArg(description = "Volume") Long volume,
            @McpArg(description = "Company id") Long companyId) {
        LocalDateTime ldt = date != null ? LocalDateTime.parse(date) : LocalDateTime.now();
        return stockClient.create(new StockRequest(ldt, openValue, highValue, lowValue, closeValue, volume, companyId));
    }

    @McpTool(name = "recalculate_company_price", description = "Update company's current price from latest quotation")
    public String recalcCompanyPrice(@McpArg(description = "Company id") Long companyId) {
        stockClient.updateCompanyPrice(companyId);
        return "Triggered price recalculation for company=" + companyId;
    }
}
