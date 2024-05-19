import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl: string = "http://localhost:8080/api/products";

  constructor(private httpClient: HttpClient) { }
  
  getProductList(categoryId: number): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search?categoryId=${categoryId}`
    return this.httpClient.get<Product[]>(searchUrl);
  }
}
