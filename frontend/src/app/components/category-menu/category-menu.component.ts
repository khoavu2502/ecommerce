import { Component, OnInit } from '@angular/core';
import { Category } from '../../common/category';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-category-menu',
  templateUrl: './category-menu.component.html',
  styleUrl: './category-menu.component.css'
})
export class CategoryMenuComponent implements OnInit {
  
  categories: Category[] = [];

  constructor(private categoryService: CategoryService) {}
  
  ngOnInit(): void {
    this.listCategories();
  }

  listCategories() {
    this.categoryService.getCategories().subscribe(response => {
      this.categories = response;
    });
  }
}
